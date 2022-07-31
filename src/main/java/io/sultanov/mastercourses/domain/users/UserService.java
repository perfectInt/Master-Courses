package io.sultanov.mastercourses.domain.users;

import io.sultanov.mastercourses.api.dtos.*;
import io.sultanov.mastercourses.enums.UserRole;
import io.sultanov.mastercourses.exceptions.passwords.DifferentPasswordsException;
import io.sultanov.mastercourses.exceptions.passwords.OldNewPasswordException;
import io.sultanov.mastercourses.exceptions.users.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserDTO userDTO) {
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        if (user.isPresent())
            throw new UserAlreadyExistsException();
        if (userRepository.count() == 0) {
            if (Objects.equals(userDTO.getPassword(), userDTO.getCheckPassword())) {
                return userRepository.save(new User()
                        .setEmail(userDTO.getEmail())
                        .setName(userDTO.getName())
                        .setPassword(passwordEncoder.encode(userDTO.getPassword()))
                        .setRoles(Set.of(UserRole.ROLE_ADMIN)));
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        else {
            if (Objects.equals(userDTO.getPassword(), userDTO.getCheckPassword())) {
                return userRepository.save(new User()
                        .setEmail(userDTO.getEmail())
                        .setName(userDTO.getName())
                        .setPassword(passwordEncoder.encode(userDTO.getPassword()))
                        .setRoles(Set.of(UserRole.ROLE_USER)));
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
    }

    public ResponseEntity<?> delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (user.getRoles().contains(UserRole.ROLE_ADMIN))
            throw new RemoveAdminRoleException();
        userRepository.delete(user);
        return ResponseEntity.ok(Map.of("status", "deleted!"));
    }

    public ResponseEntity<?> changePass(UserDetails userDetails, PasswordDTO passwordDTO) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(user.getPassword(), passwordDTO.getNewPassword())) {
            if (Objects.equals(passwordDTO.getNewPassword(), passwordDTO.getRepeatPassword())) {
                user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
                userRepository.save(user);
                return ResponseEntity.ok(Map.of("status", "successful!"));
            }
            throw new DifferentPasswordsException();
        }
        throw new OldNewPasswordException();
    }

    public ResponseEntity<?> changeName(UserDetails userDetails, NameDTO nameDTO) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        if (Objects.equals(user.getName(), nameDTO.getName()))
            throw new NewNameException();
        user.setName(nameDTO.getName());
        userRepository.save(user);
        return ResponseEntity.ok(Map.of(
                "status", "name has been successfully changed"
        ));
    }

    public ResponseEntity<?> changeAccess(Long id, AccessDTO accessDTO) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setAccessibility(accessDTO.getAccess());
        userRepository.save(user);
        return ResponseEntity.ok(Map.of(
                "status", "changed successfully"
        ));
    }

    public ResponseEntity<?> changeRole(Long id, ChangeRoleDTO changeRoleDTO) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (user.getRoles().contains(UserRole.ROLE_ADMIN))
            throw new ChangeAdminRoleException();
        if (changeRoleDTO.getRoles().contains("MODERATOR"))
            user.setRoles(Set.of(UserRole.ROLE_MODERATOR));
        else if (changeRoleDTO.getRoles().contains("USER"))
            user.setRoles(Set.of(UserRole.ROLE_USER));
        else if (changeRoleDTO.getRoles().contains("ADMIN"))
            user.setRoles(Set.of(UserRole.ROLE_ADMIN));
        return ResponseEntity.ok(Map.of(
                "status", "user roles has been changed!"
        ));
    }
}
