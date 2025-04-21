package com.example.pettravelservice.service;

import com.example.pettravelservice.request.CreateRoleRequest;
import com.example.pettravelservice.request.UpdateRoleRequest;
import com.example.pettravelservice.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> getRoles();
    Role getRoleById(Long id);
    Role createRole(CreateRoleRequest request);
    Role updateRole(UpdateRoleRequest request);
    void deleteRole(Long id);
}
