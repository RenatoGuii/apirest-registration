package com.example.registration.dtos;

import jakarta.validation.constraints.NotBlank;

public record StudentDTO(@NotBlank String name, @NotBlank String email) {
}
