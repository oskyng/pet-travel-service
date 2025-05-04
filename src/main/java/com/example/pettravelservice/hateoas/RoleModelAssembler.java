package com.example.pettravelservice.hateoas;

import com.example.pettravelservice.controller.RoleController;
import com.example.pettravelservice.model.Role;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RoleModelAssembler implements RepresentationModelAssembler<Role, EntityModel<Role>> {
    @Override
    public EntityModel<Role> toModel(Role entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(RoleController.class).getRoleById(entity.getId())).withSelfRel(),
                linkTo(methodOn(RoleController.class).getRoles()).withRel("All roles"),
                linkTo(methodOn(RoleController.class).updateRole(null)).withRel("Update role"),
                linkTo(methodOn(RoleController.class).deleteRole(entity.getId())).withRel("Delete role")
        );
    }
}
