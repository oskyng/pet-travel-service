package com.example.pettravelservice.controller;

import com.example.pettravelservice.request.CreateUserRequest;
import com.example.pettravelservice.request.LoginRequest;
import com.example.pettravelservice.request.ResponseWrapper;
import com.example.pettravelservice.request.UpdateUserRequest;
import com.example.pettravelservice.model.User;
import com.example.pettravelservice.service.IUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        log.info("GET /user - Obteniendo todos los usuarios");
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "Usuarios recuperados exitosamente", userService.getUsers().size(), userService.getUsers()));
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        log.info("GET /user/{} - Obteniendo usuario con id: {}", id);
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        log.info("GET /user/{} - Obteniendo usuario con email: {}", email);
        return userService.getUserByEmail(email);
    }

    @PostMapping("/login")
    public User loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("POST /user - Inicio de sesion usuario con email: {} y password: {}", loginRequest.getEmail(), loginRequest.getPassword().replaceAll(".", "*"));
        return userService.login(loginRequest);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request) {
        log.info("POST /user - Creando usuario con email: {}", request.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(HttpStatus.CREATED.value(), "Usuario creado exitosamente", 1, List.of(userService.createUser(request))));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserRequest request) {
        log.info("PUT /user - Actualizando usuario con email: {}", request.getEmail());
        return ResponseEntity.ok().body(new ResponseWrapper<>(HttpStatus.OK.value(), "Usuario actualizado exitosamente", 1, List.of(userService.updateUser(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.warn("DELETE /user/{} - Eliminando usuario con id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok().body(new ResponseWrapper<>(HttpStatus.OK.value(), "Usuario eliminado exitosamente", 1, null));
    }
}
