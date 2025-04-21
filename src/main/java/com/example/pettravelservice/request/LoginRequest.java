package com.example.pettravelservice.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "El nombre del usuario no puede estar vacío")
    @Size(min = 1, max = 100, message = "El email debe tener entre 1 y 100 caracteres")
    @Email
    private String email;
    @NotBlank(message = "El nombre del usuario no puede estar vacío")
    @Size(min = 1, max = 100, message = "La password debe tener entre 1 y 100 caracteres")
    private String password;
}
