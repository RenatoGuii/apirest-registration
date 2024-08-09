package com.example.registration.services;

import com.example.registration.controllers.CourseController;
import com.example.registration.dtos.CourseDTO;
import com.example.registration.entities.CourseEntity;
import com.example.registration.exceptions.EntityNotFoundException;
import com.example.registration.repositories.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public void postCourse(CourseEntity data) {
        courseRepository.save(data);
    }

    public CourseEntity getOneCourse(String id) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            throw new EntityNotFoundException("A course with this identification was not found");
        }
        return optionalCourse.get();
    }

    public List<CourseEntity> getAllCourses() {
        List<CourseEntity> listCourses = courseRepository.findAll();
        if (listCourses.isEmpty()) {
            throw new EntityNotFoundException("No course records were found");
        }
        for (CourseEntity course : listCourses) {
            course.add(linkTo(methodOn(CourseController.class).getOneCourse(course.getId())).withSelfRel());
        }
        return listCourses;
    }

    public CourseEntity updateCourse(String id, CourseDTO data) {
        CourseEntity course = getOneCourse(id);
        BeanUtils.copyProperties(data, course);
        postCourse(course);
        return course;
    }

    public Boolean deleteCourse(String id) {
        CourseEntity course = getOneCourse(id);
        courseRepository.delete(course);
        return true;
    }

}
