package com.example.registration.repositories;

import com.example.registration.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserDetails findByUsername(String username);
    UserDetails findByEmail(String email);
}
