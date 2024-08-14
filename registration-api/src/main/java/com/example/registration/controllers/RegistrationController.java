package com.example.registration.controllers;

import com.example.registration.dtos.RegistrationDTO;
import com.example.registration.entities.RegistrationEntity;
import com.example.registration.services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationEntity> postRegistration(@RequestBody @Valid RegistrationDTO data) {
        RegistrationEntity registration = registrationService.postRegistration(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(registration);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationEntity> getOneRegistration(@PathVariable(value = "id") String id) {
        RegistrationEntity registration = registrationService.getOneRegistration(id);
        return ResponseEntity.status(HttpStatus.OK).body(registration);
    }

    @GetMapping
    public ResponseEntity<List<RegistrationEntity>> getAllRegistrations(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String courseName, @RequestParam(required = false) String studentName) {
        List<RegistrationEntity> registrationList = registrationService.getAllRegistrations(page, size, courseName, studentName);
        return ResponseEntity.status(HttpStatus.OK).body(registrationList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRegistration(@PathVariable(value = "id") String id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record successfully deleted");
    }

}
