package io.sultanov.mastercourses.api.controllers;

import io.sultanov.mastercourses.domain.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping(value = "")
    public String getAdminPage() {
        return "admin";
    }

    @DeleteMapping(value = "/user/delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }
}
