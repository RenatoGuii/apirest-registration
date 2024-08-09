package com.example.registration.dtos;

import jakarta.validation.constraints.NotBlank;

public record CourseDTO(@NotBlank String name) {
}
