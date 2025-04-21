package com.example.pettravelservice.service;

import com.example.pettravelservice.request.CreateUserRequest;
import com.example.pettravelservice.request.LoginRequest;
import com.example.pettravelservice.request.UpdateUserRequest;
import com.example.pettravelservice.model.User;

import java.util.List;

public interface IUserService {
    User login(LoginRequest request);
    List<User> getUsers();
    User getUserById(Long id);
    User getUserByEmail(String email);
    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request);
    void deleteUser(Long id);
}
