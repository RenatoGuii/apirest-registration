package com.example.registration.controllers;

import com.example.registration.dtos.CourseDTO;
import com.example.registration.entities.CourseEntity;
import com.example.registration.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseEntity> createCourse(@RequestBody @Valid CourseDTO data) {
        CourseEntity newCourse = new CourseEntity();
        BeanUtils.copyProperties(data, new CourseEntity());
        courseService.postCourse(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEntity> getOneCourse(@PathVariable(value = "id") String id) {
        CourseEntity course = courseService.getOneCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    @GetMapping
    public ResponseEntity<List<CourseEntity>> getAllCourses() {
        List<CourseEntity> listCourses = courseService.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(listCourses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseEntity> updateCourse(@PathVariable(value = "id") String id, @RequestBody @Valid CourseDTO data) {
        CourseEntity updatedCourse = courseService.updateCourse(id, data);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable (value = "id") String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record successfully deleted");
    }

}
