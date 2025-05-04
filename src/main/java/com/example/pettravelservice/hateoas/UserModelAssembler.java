package com.example.pettravelservice.hateoas;

import com.example.pettravelservice.controller.UserController;
import com.example.pettravelservice.model.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(UserController.class).getUser(entity.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getUsers()).withRel("All users"),
                linkTo(methodOn(UserController.class).updateUser(null)).withRel("Update user"),
                linkTo(methodOn(UserController.class).deleteUser(entity.getId())).withRel("Delete user")
        );
    }
}
