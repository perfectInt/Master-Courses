package io.sultanov.mastercourses.api.controllers;

import io.sultanov.mastercourses.api.dtos.NameDTO;
import io.sultanov.mastercourses.api.dtos.PasswordDTO;
import io.sultanov.mastercourses.api.dtos.UserDTO;
import io.sultanov.mastercourses.api.mappers.UserMapper;
import io.sultanov.mastercourses.api.views.UserView;
import io.sultanov.mastercourses.domain.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping(value = "/signup", produces = "application/json", consumes = "application/json")
    public UserView register(@Valid @RequestBody UserDTO userDTO) {
        return userMapper.toView(userService.register(userDTO));
    }

    @PutMapping(value = "/changepass", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> changePassword(@AuthenticationPrincipal UserDetails userDetails,
                                            @Valid @RequestBody PasswordDTO passwordDTO) {
        return userService.changePass(userDetails, passwordDTO);
    }

    @PutMapping(value = "/changename", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> changeName(@AuthenticationPrincipal UserDetails userDetails,
                                        @Valid @RequestBody NameDTO nameDTO) {
        return userService.changeName(userDetails, nameDTO);
    }
}
