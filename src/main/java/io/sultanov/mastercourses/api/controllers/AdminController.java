package io.sultanov.mastercourses.api.controllers;

import io.sultanov.mastercourses.api.dtos.AccessDTO;
import io.sultanov.mastercourses.domain.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping(value = "")
    public String getAdminPage() {
        return "admin";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/user/delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PutMapping(value = "/changeaccess/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> changeAccess(@PathVariable Long id,
                                          @Valid @RequestBody AccessDTO accessDTO) {
        return userService.changeAccess(id, accessDTO);
    }
}
