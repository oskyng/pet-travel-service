package com.example.pettravelservice.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser un número positivo")
    private Long id;
    @NotBlank(message = "El nombre del usuario no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String firstName;
    @NotBlank(message = "El nombre del usuario no puede estar vacío")
    @Size(min = 1, max = 100, message = "El apellido debe tener entre 1 y 100 caracteres")
    private String lastName;
    @NotBlank(message = "El nombre del usuario no puede estar vacío")
    @Size(min = 1, max = 100, message = "El email debe tener entre 1 y 100 caracteres")
    @Email
    private String email;
    @NotBlank(message = "El nombre del usuario no puede estar vacío")
    @Size(min = 1, max = 100, message = "La password debe tener entre 1 y 100 caracteres")
    private String password;
    @NotNull(message = "El roleId no puede ser nulo")
    @Positive(message = "El roleId debe ser un número positivo")
    private Long roleId;
}
