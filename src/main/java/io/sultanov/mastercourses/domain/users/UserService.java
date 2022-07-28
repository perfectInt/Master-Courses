package io.sultanov.mastercourses.domain.users;

import io.sultanov.mastercourses.api.dtos.UserDTO;
import io.sultanov.mastercourses.enums.UserRole;
import io.sultanov.mastercourses.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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

    public boolean checkPassword(String firstPassword, String secondPassword) {
        return passwordEncoder.matches(firstPassword, secondPassword);
    }
}
