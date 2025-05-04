package com.example.pettravelservice;

import com.example.pettravelservice.model.Role;
import com.example.pettravelservice.model.User;
import com.example.pettravelservice.repository.IRoleRepository;
import com.example.pettravelservice.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;

    @Test
    public void testSaveAndFindByEmail() {
        Role role = new Role(null, "Admin");
        Role saveRole = roleRepository.save(role);

        User user = new User(null, "Oscar", "Sanzana", "oscar.sanzna.97@gmail.com", "123qwe", saveRole);
        User saveUser = userRepository.save(user);

        Optional<User> foundUser = userRepository.findById(saveUser.getId());

        assertTrue(foundUser.isPresent());

        assertEquals("Oscar", foundUser.get().getFirstName());
    }
}
