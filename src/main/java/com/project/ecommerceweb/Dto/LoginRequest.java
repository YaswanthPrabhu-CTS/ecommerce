package com.project.ecommerceweb.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email(message="valid email is required") String email,
        @NotBlank(message = "password is required") String password
) {
}
