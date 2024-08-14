package com.example.registration.dtos.userDtos;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(@NotBlank String username, @NotBlank String password) {
}
