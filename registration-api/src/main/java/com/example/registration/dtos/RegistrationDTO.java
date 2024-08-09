package com.example.registration.dtos;

import jakarta.validation.constraints.NotBlank;

public record RegistrationDTO(@NotBlank String student_id, @NotBlank String course_id) {
}
