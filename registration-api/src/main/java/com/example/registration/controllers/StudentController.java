package com.example.registration.controllers;

import com.example.registration.dtos.StudentDTO;
import com.example.registration.entities.StudentEntity;
import com.example.registration.services.StudentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Student", description = "APIs relacionadas a estudantes")
@RestController
@RequestMapping("/student")
// Serve apenas para mostrar no Swagger UI que é necessário o token de autenticação para o uso do endpoint
@SecurityRequirement(name = "bearerAuth")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Operation(summary = "Criar um novo estudante", description = "Cria um novo estudante com os dados fornecidos, necessário ser um usuário de nível ADMIN.")
    @ApiResponse(responseCode = "201", description = "Estudante criado com sucesso")
    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody @Valid StudentDTO data) {
        StudentEntity newStudent = new StudentEntity();
        BeanUtils.copyProperties(data, newStudent);
        studentService.postStudent(newStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @Operation(summary = "Buscar um estudante por ID", description = "Retorna o estudante correspondente ao ID fornecido, necessário ser um usuário de nível USER.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estudante encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estudante não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getOneStudent(
            @Parameter(description = "ID do estudante", required = true) @PathVariable(value = "id") String id) {
        StudentEntity student = studentService.getOneStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @Operation(summary = "Listar todos os estudantes", description = "Retorna uma lista paginada de todos os estudantes, necessário ser um usuário de nível USER.")
    @ApiResponse(responseCode = "200", description = "Lista de estudantes retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents(
            @Parameter(description = "Número da página", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Número de registros por página", example = "10") @RequestParam(defaultValue = "10") int size) {
        List<StudentEntity> listStudent = studentService.getAllStudents(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(listStudent);
    }

    @Operation(summary = "Atualizar um estudante", description = "Atualiza os dados de um estudante existente, necessário ser um usuário de nível ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estudante atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estudante não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> updateStudent(
            @Parameter(description = "ID do estudante", required = true) @PathVariable(value = "id") String id,
            @RequestBody @Valid StudentDTO data) {
        StudentEntity updatedStudent = studentService.updateStudent(id, data);
        return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
    }

    @Operation(summary = "Deletar um estudante", description = "Deleta um estudante existente pelo ID, necessário ser um usuário de nível ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estudante deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estudante não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(
            @Parameter(description = "ID do estudante", required = true) @PathVariable(value = "id") String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record successfully deleted");
    }

}
