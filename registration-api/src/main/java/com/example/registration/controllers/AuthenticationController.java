package com.example.registration.controllers;

import com.example.registration.dtos.userDtos.AuthenticationDTO;
import com.example.registration.dtos.userDtos.LoginResponseDTO;
import com.example.registration.dtos.userDtos.RegisterDTO;
import com.example.registration.repositories.UserRepository;
import com.example.registration.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data) {
        Object token = authenticationService.userLogin(data);
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token.toString()));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO data) {
        authenticationService.userRegister(data);
        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully");
    }

}
