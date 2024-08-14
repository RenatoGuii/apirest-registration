package com.example.registration.dtos.userDtos;

import com.example.registration.entities.user.UserRole;

public record RegisterDTO(String username, String password, String email, UserRole role) {
}
