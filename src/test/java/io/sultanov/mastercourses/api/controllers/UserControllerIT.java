package io.sultanov.mastercourses.api.controllers;

import io.sultanov.mastercourses.api.dtos.UserDTO;
import io.sultanov.mastercourses.domain.users.User;
import io.sultanov.mastercourses.domain.users.UserRepository;
import io.sultanov.mastercourses.enums.UserRole;
import io.sultanov.mastercourses.exceptions.users.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserControllerIT {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserController userController;

    @Test
    public void registerTest() {
        UserDTO newUser = new UserDTO();
        newUser.setName("Olof");
        newUser.setEmail("olofcool@gmail.com");
        newUser.setPassword("12345678");
        newUser.setCheckPassword("12345678");
        userController.register(newUser);
        User user = userRepository.findByEmail("olofcool@gmail.com").orElseThrow(UserNotFoundException::new);
        assertEquals(newUser.getEmail(), user.getEmail());
        assertEquals(newUser.getName(), user.getName());
        assertEquals(true, user.getAccessibility());
        assertEquals(UserRole.ADMIN, user.getRole());

        UserDTO newUserSecond = new UserDTO();
        newUserSecond.setName("Ruslan");
        newUserSecond.setEmail("rulik@gmail.com");
        newUserSecond.setPassword("12345678");
        newUserSecond.setCheckPassword("12345678");
        userController.register(newUserSecond);
        User secondUser = userRepository.findByEmail("rulik@gmail.com").orElseThrow(UserNotFoundException::new);
        assertEquals(newUserSecond.getEmail(), secondUser.getEmail());
        assertEquals(newUserSecond.getName(), secondUser.getName());
        assertEquals(false, secondUser.getAccessibility());
        assertEquals(UserRole.USER, secondUser.getRole());
    }
}