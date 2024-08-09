package com.example.registration.services;

import com.example.registration.controllers.RegistrationController;
import com.example.registration.dtos.RegistrationDTO;
import com.example.registration.entities.CourseEntity;
import com.example.registration.entities.RegistrationEntity;
import com.example.registration.entities.StudentEntity;
import com.example.registration.exceptions.EntityNotFoundException;
import com.example.registration.repositories.CourseRepository;
import com.example.registration.repositories.RegistrationRepository;
import com.example.registration.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class RegistrationService {

    private static final Logger log = LoggerFactory.getLogger(RegistrationService.class);
    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    public RegistrationEntity postRegistration(RegistrationDTO data) {

        StudentEntity student = studentRepository.findById(data.student_id())
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        CourseEntity course = courseRepository.findById(data.course_id())
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        RegistrationEntity registration = new RegistrationEntity();
        registration.setCourse(course);
        registration.setStudent(student);
        registrationRepository.save(registration);
        return registration;
    }

    public RegistrationEntity getOneRegistration(String id) {
        Optional<RegistrationEntity> optionalRegistration = registrationRepository.findById(id);
        if (optionalRegistration.isEmpty()) {
            throw new EntityNotFoundException("A registration with this identification was not found");
        }
        return optionalRegistration.get();
    }

    public List<RegistrationEntity> getAllRegistrations() {
        List<RegistrationEntity> registrationList = registrationRepository.findAll();
        if (registrationList.isEmpty()) {
            throw new EntityNotFoundException("No course records were found");
        }
        for (RegistrationEntity registration : registrationList) {
            registration.add(linkTo(methodOn(RegistrationController.class).getOneRegistration(registration.getId())).withSelfRel());
        }
        return registrationList;
    }

    public Boolean deleteRegistration(String id) {
        RegistrationEntity registration = getOneRegistration(id);
        registrationRepository.delete(registration);
        return true;
    }

}
