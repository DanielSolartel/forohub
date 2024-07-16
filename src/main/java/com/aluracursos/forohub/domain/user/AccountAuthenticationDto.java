package com.aluracursos.forohub.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AccountAuthenticationDto(
        @NotBlank
        String username,
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9]{5,}$", message = "Must be alphanumeric with a minimum of 5 characters")
        String password
) {
}