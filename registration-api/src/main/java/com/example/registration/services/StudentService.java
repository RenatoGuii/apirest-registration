package com.example.registration.services;

import com.example.registration.controllers.StudentController;
import com.example.registration.dtos.StudentDTO;
import com.example.registration.entities.StudentEntity;
import com.example.registration.exceptions.EntityNotFoundException;
import com.example.registration.repositories.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void postStudent (StudentEntity data) {
        studentRepository.save(data);
    }

    public StudentEntity getOneStudent(String id) {
        Optional<StudentEntity> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new EntityNotFoundException("A student with this identification was not found");
        }
        return student.get();
    }

    public List<StudentEntity> getAllStudents() {
        List<StudentEntity> listStudents = studentRepository.findAll();
        if (listStudents.isEmpty()) {
            throw new EntityNotFoundException("No student records were found");
        }
        for (StudentEntity student : listStudents) {
            student.add(linkTo(methodOn(StudentController.class).getOneStudent(student.getId())).withSelfRel());
        }
        return listStudents;
    }

    public StudentEntity updateStudent (String id, StudentDTO data) {
        StudentEntity student = getOneStudent(id);
        BeanUtils.copyProperties(data, student);
        postStudent(student);
        return student;
    }

    public Boolean deleteStudent (String id) {
        StudentEntity student = getOneStudent(id);
        studentRepository.delete(student);
        return true;
    }

}
