package com.example.pettravelservice.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest {
    @NotBlank(message = "El nombre del role no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre del role debe tener entre 1 y 100 caracteres")
    private String roleName;
}
