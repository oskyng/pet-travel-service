package com.example.pettravelservice.controller;

import com.example.pettravelservice.model.Role;
import com.example.pettravelservice.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController() {
        this.roleService = new RoleService();
    }

    @GetMapping()
    public List<Role> getRoles() {
        return roleService.getRoles();
    }
    
    @GetMapping("/{id}")
    Optional<Role> getRoleById(@PathVariable Integer id) {
        return roleService.getRole(id);
    }
}
