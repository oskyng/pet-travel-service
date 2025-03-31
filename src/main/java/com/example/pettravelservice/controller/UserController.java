package com.example.pettravelservice.controller;

import com.example.pettravelservice.entity.LoginRequest;
import com.example.pettravelservice.model.User;
import com.example.pettravelservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController () {
        this.userService = new UserService();
    }

    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    Optional<User> getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/login")
    Optional<User> loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

}
