package com.example.registration.controllers;

import com.example.registration.dtos.StudentDTO;
import com.example.registration.entities.StudentEntity;
import com.example.registration.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    /*
    GET /alunos: Listar todos os alunos.
    GET /alunos/{id}: Consultar detalhes de um aluno específico.
    PUT /alunos/{id}: Atualizar informações de um aluno.
    DELETE /alunos/{id}: Remover um aluno do sistema.
     */

    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody @Valid StudentDTO data) {
        StudentEntity newStudent = new StudentEntity();
        BeanUtils.copyProperties(data, newStudent);
        studentService.postStudent(newStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

}
