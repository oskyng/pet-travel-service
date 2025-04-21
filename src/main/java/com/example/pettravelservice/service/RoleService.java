package com.example.pettravelservice.service;

import com.example.pettravelservice.request.CreateRoleRequest;
import com.example.pettravelservice.request.UpdateRoleRequest;
import com.example.pettravelservice.exception.RoleNotFoundException;
import com.example.pettravelservice.model.Role;
import com.example.pettravelservice.repository.IRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;

    public List<Role> getRoles() {
        log.debug("Servicio: getRoles()");
        return roleRepository.findAll(Sort.by("id").ascending());
    }

    public Role getRoleById(Long id) {
        log.debug("Servicio: getRoleById({})", id);
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
    }

    public Role createRole(CreateRoleRequest request) {
        log.debug("Servicio: createRole({})", request.getRoleName());
        Role role = new Role();
        role.setRoleName(request.getRoleName());
        return roleRepository.save(role);
    }

    public Role updateRole(UpdateRoleRequest request) {
        log.debug("Servicio: updateRole({})", request.getId());
        Optional<Role> foundRole = roleRepository.findById(request.getId());
        return foundRole.map(role -> {
            role.setRoleName(request.getRoleName() == null ? role.getRoleName() : request.getRoleName());
            return roleRepository.save(role);
        }).orElseThrow(() -> new RoleNotFoundException(request.getId()));
    }

    public void deleteRole(Long id) {
        log.debug("Servicio: deleteRole({})", id);
        Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
        roleRepository.delete(role);
    }
}
