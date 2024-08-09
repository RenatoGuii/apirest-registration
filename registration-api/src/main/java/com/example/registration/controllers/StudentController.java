package com.example.registration.controllers;

import com.example.registration.dtos.StudentDTO;
import com.example.registration.entities.StudentEntity;
import com.example.registration.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody @Valid StudentDTO data) {
        StudentEntity newStudent = new StudentEntity();
        BeanUtils.copyProperties(data, newStudent);
        studentService.postStudent(newStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getOneStudent(@PathVariable(value = "id") String id) {
        StudentEntity student = studentService.getOneStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        List<StudentEntity> listStudent = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(listStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable(value = "id") String id, @RequestBody @Valid StudentDTO data) {
        StudentEntity updatedStudent = studentService.updateStudent(id, data);
        studentService.postStudent(updatedStudent);
        return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable(value = "id") String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record successfully deleted");
    }

}
