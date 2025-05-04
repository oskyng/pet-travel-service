package com.example.pettravelservice.controller;

import com.example.pettravelservice.hateoas.RoleModelAssembler;
import com.example.pettravelservice.request.CreateRoleRequest;
import com.example.pettravelservice.request.ResponseWrapper;
import com.example.pettravelservice.request.UpdateRoleRequest;
import com.example.pettravelservice.model.Role;
import com.example.pettravelservice.service.IRoleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {
    private final IRoleService roleService;
    private final RoleModelAssembler roleModelAssembler;

    public RoleController(IRoleService roleService, RoleModelAssembler roleModelAssembler) {
        this.roleService = roleService;
        this.roleModelAssembler = roleModelAssembler;
    }

    @GetMapping()
    public ResponseEntity<?> getRoles() {
        log.info("GET /role - Obteniendo todos los roles");
        List<EntityModel<Role>> roles = roleService.getRoles().stream().map(roleModelAssembler::toModel).toList();
        return ResponseEntity.ok(CollectionModel.of(roles, linkTo(methodOn(RoleController.class).getRoles()).withSelfRel()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        log.info("GET /role/{} - Obteniendo role con id: {}", id);
        return ResponseEntity.ok(roleModelAssembler.toModel(roleService.getRoleById(id)));
    }

    @PostMapping
    public ResponseEntity<?> createRole(@Valid @RequestBody CreateRoleRequest request) {
        log.info("POST /role - Creando role con nombre: {}", request.getRoleName());
        return ResponseEntity.status(HttpStatus.CREATED).body(roleModelAssembler.toModel(roleService.createRole(request)));
    }

    @PutMapping
    public ResponseEntity<?> updateRole(@Valid @RequestBody UpdateRoleRequest request) {
        log.info("PUT /role - Actualizando role con id: {}", request.getId());
        return ResponseEntity.status(HttpStatus.OK).body(roleModelAssembler.toModel(roleService.updateRole(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        log.warn("DELETE /role/{} - Eliminando role con id: {}", id);
        roleService.deleteRole(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(HttpStatus.OK.value(), "Role eliminado exitosamente", 1, List.of()));
    }
}
