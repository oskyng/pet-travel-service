package com.example.pettravelservice.service;

import com.example.pettravelservice.model.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    public final List<Role> roles = new ArrayList<>();

    public RoleService() {
        roles.add(new Role(1, "admin"));
        roles.add(new Role(2, "dueño de mascota"));
        roles.add(new Role(3, "conductor de transporte pet-friendly"));
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Optional<Role> getRole(Integer id) {
        return roles.stream().filter(role -> role.getId().equals(id)).findFirst();
    }

    public Role save(Role role) {
        roles.add(role);
        return role;
    }

    public Role update(Role role) {
        Optional<Role> optionalRole = getRole(role.getId());
        return optionalRole.map(r-> {
            r.setRoleName(role.getRoleName());
            return r;
        }).orElse(null);
    }

    public void delete(Integer id) {
        Optional<Role> optionalRole = getRole(id);
        roles.remove(optionalRole.orElse(null));
    }
}
