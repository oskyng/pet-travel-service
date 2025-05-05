package com.example.pettravelservice.service;

import com.example.pettravelservice.request.CreateUserRequest;
import com.example.pettravelservice.request.LoginRequest;
import com.example.pettravelservice.request.UpdateUserRequest;
import com.example.pettravelservice.exception.RoleNotFoundException;
import com.example.pettravelservice.exception.UserNotFoundException;
import com.example.pettravelservice.model.User;
import com.example.pettravelservice.repository.IRoleRepository;
import com.example.pettravelservice.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements IUserService {
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;

    public UserService(IRoleRepository roleRepository, IUserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User login(LoginRequest request) {
        log.debug("Servicio: login()");
        return userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword()).orElseThrow(() -> new UserNotFoundException("Usuario y/o contrasena incorrecto"));
    }

    @Override
    public List<User> getUsers() {
        log.debug("Servicio: getUsers()");
        return userRepository.findAll(Sort.by("email").ascending());
    }

    @Override
    public User getUserById(Long id) {
        log.debug("Servicio: getUserById({})", id);
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getUserByEmail(String email) {
        log.debug("Servicio: getUserByEmail({})", email);
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con email: "+email));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        log.debug("Servicio: createUser({})", request.getEmail());
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(roleRepository.findById(request.getRoleId()).orElseThrow(() -> new RoleNotFoundException(request.getRoleId())));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UpdateUserRequest request) {
        log.debug("Servicio: updateUser({})", request.getEmail());
        Optional<User> foundUser = userRepository.findById(request.getId());
        return foundUser.map(user -> {
            user.setFirstName(request.getFirstName() != null ? request.getFirstName() : user.getFirstName());
            user.setLastName(request.getLastName() != null ? request.getLastName() : user.getLastName());
            user.setEmail(request.getEmail() != null ? request.getEmail() : user.getEmail());
            user.setPassword(request.getPassword() != null ? request.getPassword() : user.getPassword());
            user.setRole(request.getRoleId() != null ? roleRepository.findById(request.getRoleId()).orElseThrow(() -> new RoleNotFoundException(request.getRoleId())) : user.getRole());

            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(request.getId()));
    }

    @Override
    public void deleteUser(Long id) {
        log.debug("Servicio: deleteUser({})", id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }
}
