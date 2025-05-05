package com.example.pettravelservice;

import com.example.pettravelservice.exception.UserNotFoundException;
import com.example.pettravelservice.model.Role;
import com.example.pettravelservice.model.User;
import com.example.pettravelservice.repository.IRoleRepository;
import com.example.pettravelservice.repository.IUserRepository;
import com.example.pettravelservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private IUserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(IUserRepository.class);
        IRoleRepository roleRepository = mock(IRoleRepository.class);
        userService = new UserService(roleRepository, userRepository);
    }

    @Test
    public void getUsers() {
        Role role1 = new Role(1L, "Admin");
        Role role2 = new Role(2L, "Test");

        User user1 = new User(1L, "Oscar", "Sanzana", "oscar.sanzana.97@gmail.com", "123qwe", role1);
        User user2 = new User(2L, "Prueba", "test", "o.sanzana@gmail.com", "123qwe", role2);

        when(userRepository.findAll(Sort.by("email").ascending())).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getUsers();

        assertEquals(2, users.size());
        assertEquals("oscar.sanzana.97@gmail.com", users.get(0).getEmail());
    }

    @Test
    public void testGetUserById() {
        Role role = new Role(1L, "Admin");
        User user = new User(1L, "Oscar", "Sanzana", "oscar.sanzana.97@gmail.com", "123qwe", role);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User resultado = userService.getUserById(1L);

        assertEquals("oscar.sanzana.97@gmail.com", resultado.getEmail());
        assertEquals("Admin", resultado.getRole().getRoleName());
    }

    @Test
    public void testGetUserByIdNotFound () {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(99L));
    }

}
