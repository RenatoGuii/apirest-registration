package com.example.registration.controllers;

import com.example.registration.dtos.RegistrationDTO;
import com.example.registration.entities.RegistrationEntity;
import com.example.registration.services.RegistrationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

import java.util.List;

@Tag(name = "Registration", description = "APIs relacionadas ao gerenciamento de matrículas")
@RestController
@RequestMapping("/registration")
// Serve apenas para mostrar no Swagger UI que é necessário o token de autenticação para o uso do endpoint
@SecurityRequirement(name = "bearerAuth")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @Operation(summary = "Criar matrícula", description = "Cria uma nova matrícula para um aluno em um curso, necessário ser um usuário de nível ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Matrícula criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criar a matrícula")
    })
    @PostMapping
    public ResponseEntity<RegistrationEntity> postRegistration(
            @Parameter(description = "Dados da nova matrícula", required = true) @RequestBody @Valid RegistrationDTO data) {
        RegistrationEntity registration = registrationService.postRegistration(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(registration);
    }

    @Operation(summary = "Buscar matrícula", description = "Busca uma matrícula específica pelo ID, necessário ser um usuário de nível USER.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Matrícula encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationEntity> getOneRegistration(
            @Parameter(description = "ID da matrícula", required = true) @PathVariable(value = "id") String id) {
        RegistrationEntity registration = registrationService.getOneRegistration(id);
        return ResponseEntity.status(HttpStatus.OK).body(registration);
    }

    @Operation(summary = "Listar matrículas", description = "Lista todas as matrículas com filtros opcionais e paginação, necessário ser um usuário de nível USER.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de matrículas retornada com sucesso"),
    })
    @GetMapping
    public ResponseEntity<List<RegistrationEntity>> getAllRegistrations(
            @Parameter(description = "Número da página") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamanho da página") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Nome do curso para filtrar") @RequestParam(required = false) String courseName,
            @Parameter(description = "Nome do aluno para filtrar") @RequestParam(required = false) String studentName) {
        List<RegistrationEntity> registrationList = registrationService.getAllRegistrations(page, size, courseName, studentName);
        return ResponseEntity.status(HttpStatus.OK).body(registrationList);
    }

    @Operation(summary = "Deletar matrícula", description = "Deleta uma matrícula específica pelo ID, necessário ser um usuário de nível ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Matrícula deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRegistration(
            @Parameter(description = "ID da matrícula", required = true) @PathVariable(value = "id") String id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record successfully deleted");
    }

}
