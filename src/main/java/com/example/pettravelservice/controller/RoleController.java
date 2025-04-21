package com.example.pettravelservice.controller;

import com.example.pettravelservice.request.CreateRoleRequest;
import com.example.pettravelservice.request.ResponseWrapper;
import com.example.pettravelservice.request.UpdateRoleRequest;
import com.example.pettravelservice.model.Role;
import com.example.pettravelservice.service.IRoleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {
    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<?> getRoles() {
        log.info("GET /role - Obteniendo todos los roles");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(HttpStatus.OK.value(), "Roles recuperados exitosamente", roleService.getRoles().size(), roleService.getRoles()));
    }
    
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        log.info("GET /role/{} - Obteniendo role con id: {}", id);
        return roleService.getRoleById(id);
    }

    @PostMapping
    public ResponseEntity<?> createRole(@Valid @RequestBody CreateRoleRequest request) {
        log.info("POST /role - Creando role con nombre: {}", request.getRoleName());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(HttpStatus.CREATED.value(), "Role creado exitosamente", 1, List.of(roleService.createRole(request))));
    }

    @PutMapping
    public ResponseEntity<?> updateRole(@Valid @RequestBody UpdateRoleRequest request) {
        log.info("PUT /role - Actualizando role con id: {}", request.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(HttpStatus.OK.value(), "Role actualizado exitosamente", 1, List.of(roleService.updateRole(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        log.warn("DELETE /role/{} - Eliminando role con id: {}", id);
        roleService.deleteRole(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(HttpStatus.OK.value(), "Role eliminado exitosamente", 1, List.of()));
    }
}
