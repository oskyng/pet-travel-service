package com.example.pettravelservice.controller;

import com.example.pettravelservice.hateoas.UserModelAssembler;
import com.example.pettravelservice.request.CreateUserRequest;
import com.example.pettravelservice.request.LoginRequest;
import com.example.pettravelservice.request.ResponseWrapper;
import com.example.pettravelservice.request.UpdateUserRequest;
import com.example.pettravelservice.model.User;
import com.example.pettravelservice.service.IUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;
    private final UserModelAssembler userModelAssembler;

    public UserController(IUserService userService, UserModelAssembler userModelAssembler) {
        this.userService = userService;
        this.userModelAssembler = userModelAssembler;
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        log.info("GET /user - Obteniendo todos los usuarios");
        List<EntityModel<User>> users = userService.getUsers().stream().map(userModelAssembler::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(users, linkTo(methodOn(UserController.class).getUsers()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        log.info("GET /user/{} - Obteniendo usuario con id: {}", id);
        return ResponseEntity.ok(userModelAssembler.toModel(userService.getUserById(id)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        log.info("GET /user/{} - Obteniendo usuario con email: {}", email);
        return ResponseEntity.ok(userModelAssembler.toModel(userService.getUserByEmail(email)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("POST /user - Inicio de sesion usuario con email: {} y password: {}", loginRequest.getEmail(), loginRequest.getPassword().replaceAll(".", "*"));
        return ResponseEntity.ok(userModelAssembler.toModel(userService.login(loginRequest)));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request) {
        log.info("POST /user - Creando usuario con email: {}", request.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userModelAssembler.toModel(userService.createUser(request)));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserRequest request) {
        log.info("PUT /user - Actualizando usuario con email: {}", request.getEmail());
        return ResponseEntity.ok().body(userModelAssembler.toModel(userService.updateUser(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.warn("DELETE /user/{} - Eliminando usuario con id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok().body(new ResponseWrapper<>(HttpStatus.OK.value(), "Usuario eliminado exitosamente", 1, null));
    }
}
