package com.amir.taskmanager.controller;


import com.amir.taskmanager.model.Role;
import com.amir.taskmanager.model.User;
import com.amir.taskmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        user.setRole(Role.USER);
        return userService.createUser(user);
    }
}
