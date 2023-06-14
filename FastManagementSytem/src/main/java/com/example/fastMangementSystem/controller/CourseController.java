package com.example.fastMangementSystem.controller;

import com.example.fastMangementSystem.dto.course.CourseCreatedDto;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.exception.RequestValidationException;
import com.example.fastMangementSystem.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course/v1")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<CourseEntity> add(
            @RequestBody CourseCreatedDto courseCreatedDto,
            @RequestParam UUID adminId,
            @RequestParam String role,
            BindingResult bindingResult
            ){
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(courseService.add(courseCreatedDto, adminId, role));
    }

    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public void delete(
            @RequestParam UUID id
    ){
         courseService.deleteById(id);
    }

    @GetMapping("/get-user-courses")
    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('STUDENT') or hasRole('SUPER_ADMIN') " )
    public ResponseEntity<List<CourseEntity>> getUserCourses(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam UUID userId
    ){
        return ResponseEntity.ok(courseService.getUserCourse(size, page, userId));
    }

    @PatchMapping("/{courseId}/update")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<CourseEntity> updateCourse(
            @RequestBody CourseCreatedDto courseCreatedDto,
            @PathVariable UUID courseId) {
        return ResponseEntity.ok(courseService.update(courseCreatedDto, courseId));
    }

    @GetMapping("/get-all-courses")
    public ResponseEntity<List<CourseEntity>> getAll() {
        return ResponseEntity.ok( courseService.getAllCourses());
    }


}
