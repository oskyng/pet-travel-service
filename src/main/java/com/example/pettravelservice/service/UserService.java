package com.example.pettravelservice.service;

import com.example.pettravelservice.model.Role;
import com.example.pettravelservice.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    public final List<Role> roles = new ArrayList<>();

    public UserService() {
        roles.add(new Role(1, "admin"));
        roles.add(new Role(2, "dueño de mascota"));
        roles.add(new Role(3, "conductor de transporte pet-friendly"));

        users.add(new User(1, "Oscar", "Sanzana", "oscar.sanzana.97@gmail.com", "123Admin#", roles.get(0)));
        users.add(new User(2, "Ellen", "Joe", "ellen.doe@gmail.com", "123afv#", roles.get(1)));
        users.add(new User(3, "Nicole", "Demara", "nicole.demara@gmail.com", "aqea4621n#", roles.get(2)));
    }

    public List<User> getUsers() {
        return users;
    }

    public Optional<User> getUser(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public Optional<User> login(String email, String password) {
        return users.stream().filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password)).findFirst();
    }
}
