package io.sultanov.mastercourses.api.controllers;

import io.sultanov.mastercourses.api.dtos.UserDTO;
import io.sultanov.mastercourses.api.mappers.UserMapper;
import io.sultanov.mastercourses.api.views.UserView;
import io.sultanov.mastercourses.domain.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping(value = "/user/delete/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }
}
