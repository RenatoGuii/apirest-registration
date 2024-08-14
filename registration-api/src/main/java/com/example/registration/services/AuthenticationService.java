package com.example.registration.services;

import com.example.registration.dtos.userDtos.AuthenticationDTO;
import com.example.registration.dtos.userDtos.RegisterDTO;
import com.example.registration.entities.user.UserEntity;
import com.example.registration.exceptions.AuthenticationException;
import com.example.registration.infra.security.TokenService;
import com.example.registration.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    public Object userLogin(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        // Gerar Token JWT
        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return token;
    }

    public void userRegister(RegisterDTO data) {
        if (userRepository.findByEmail(data.email()) != null) {
            throw new AuthenticationException("There is already a user using this email!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserEntity newUser = new UserEntity(data.username(), encryptedPassword, data.email(), data.role());
        userRepository.save(newUser);
    }

}
