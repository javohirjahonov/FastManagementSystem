package com.example.fastMangementSystem.controller;

import com.example.fastMangementSystem.dto.course.CourseCreatedDto;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.exception.RequestValidationException;
import com.example.fastMangementSystem.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CourseEntity> add(
            @RequestBody CourseCreatedDto courseCreatedDto,
            @RequestParam UUID userId,
            BindingResult bindingResult
            ){
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(courseService.add(courseCreatedDto,userId));
    }

    @DeleteMapping("/delete")
    public void delete(
            @RequestParam UUID id
    ){
         courseService.deleteById(id);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CourseEntity>> getAll(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam UUID userId
    ){
        return ResponseEntity.ok(courseService.getUserCourse(size, page, userId));
    }

    @PatchMapping("/{courseId}/update")
    public ResponseEntity<CourseEntity> updateCourse(
            @RequestBody CourseCreatedDto courseCreatedDto,
            @PathVariable UUID courseId) {
        return ResponseEntity.ok(courseService.update(courseCreatedDto, courseId));
    }





}
