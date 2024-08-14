package com.example.registration.controllers;

import com.example.registration.dtos.CourseDTO;
import com.example.registration.entities.CourseEntity;
import com.example.registration.services.CourseService;
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

@Tag(name = "Course", description = "APIs relacionadas ao gerenciamento de cursos")
@RestController
@RequestMapping("course")
// Serve apenas para mostrar no Swagger UI que é necessário o token de autenticação para o uso do endpoint
@SecurityRequirement(name = "bearerAuth")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Operation(summary = "Criação de curso", description = "Cria um novo curso no sistema, necessário ser um usuário de nível ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Curso criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação do curso")
    })
    @PostMapping
    public ResponseEntity<CourseEntity> createCourse(
            @Parameter(description = "Dados do novo curso", required = true) @RequestBody @Valid CourseDTO data) {
        CourseEntity newCourse = new CourseEntity();
        BeanUtils.copyProperties(data, newCourse);
        courseService.postCourse(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCourse);
    }

    @Operation(summary = "Busca de curso", description = "Busca um curso específico pelo ID, necessário ser um usuário de nível USER.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Curso encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CourseEntity> getOneCourse(
            @Parameter(description = "ID do curso", required = true) @PathVariable(value = "id") String id) {
        CourseEntity course = courseService.getOneCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    @Operation(summary = "Listagem de cursos", description = "Lista todos os cursos com paginação, necessário ser um usuário de nível USER.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso"),
    })
    @GetMapping
    public ResponseEntity<List<CourseEntity>> getAllCourses(
            @Parameter(description = "Número da página") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamanho da página") @RequestParam(defaultValue = "10") int size) {
        List<CourseEntity> listCourses = courseService.getAllCourses(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(listCourses);
    }

    @Operation(summary = "Atualização de curso", description = "Atualiza os dados de um curso existente, necessário ser um usuário de nível ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CourseEntity> updateCourse(
            @Parameter(description = "ID do curso", required = true) @PathVariable(value = "id") String id,
            @Parameter(description = "Novos dados do curso", required = true) @RequestBody @Valid CourseDTO data) {
        CourseEntity updatedCourse = courseService.updateCourse(id, data);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCourse);
    }

    @Operation(summary = "Deleção de curso", description = "Deleta um curso específico pelo ID, necessário ser um usuário de nível ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Curso deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(
            @Parameter(description = "ID do curso", required = true) @PathVariable(value = "id") String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record successfully deleted");
    }

}
