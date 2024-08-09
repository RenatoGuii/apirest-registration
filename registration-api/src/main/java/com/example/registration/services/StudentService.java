package com.example.registration.services;

import com.example.registration.entities.StudentEntity;
import com.example.registration.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void postStudent (StudentEntity data) {
        studentRepository.save(data);
    }

}
