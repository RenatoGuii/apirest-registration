package com.example.registration.controllers;

import com.example.registration.dtos.userDtos.AuthenticationDTO;
import com.example.registration.dtos.userDtos.LoginResponseDTO;
import com.example.registration.dtos.userDtos.RegisterDTO;
import com.example.registration.repositories.UserRepository;
import com.example.registration.services.AuthenticationService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "APIs relacionadas à autenticação de usuários")
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Operation(summary = "Login de usuário", description = "Autentica um usuário com base em suas credenciais.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @Parameter(description = "Dados de autenticação do usuário", required = true) @RequestBody @Valid AuthenticationDTO data) {
        Object token = authenticationService.userLogin(data);
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token.toString()));
    }

    @Operation(summary = "Registro de usuário", description = "Registra um novo usuário no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para registro")
    })
    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @Parameter(description = "Dados de registro do usuário", required = true) @RequestBody @Valid RegisterDTO data) {
        authenticationService.userRegister(data);
        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully");
    }

}
