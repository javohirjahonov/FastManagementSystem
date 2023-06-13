package com.example.fastMangementSystem.controller;

import com.example.fastMangementSystem.dto.LessonCreateDto;
import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import com.example.fastMangementSystem.service.lesson.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lesson/v1")
@RequiredArgsConstructor
public class LessonController {
  private final LessonService lessonService;
    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<LessonEntity> add(
            @RequestBody LessonCreateDto lessonCreateDto,
            @RequestParam UUID courseId
    ){
        return ResponseEntity.ok(lessonService.add(lessonCreateDto,courseId));
    }
  @DeleteMapping("/delete")
  @PreAuthorize(value = "hasRole('ADMIN')")
  public void delete(
          @RequestParam UUID id
  ){
    lessonService.delete(id);
  }

  @GetMapping("/get-all")
  @PreAuthorize(value = "hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
  public ResponseEntity<List<LessonEntity>> getAll(
          @RequestParam int size,
          @RequestParam int page,
          @RequestParam UUID userId
  ){
    return ResponseEntity.ok(lessonService.getAll(size,page,userId));
  }

  @PatchMapping("/{lessonId}/update")
  @PreAuthorize(value = "hasRole('ADMIN')")
  public ResponseEntity<LessonEntity> updateLesson(
          @RequestBody LessonCreateDto lessonCreateDto,
          @PathVariable UUID lessonId) {
    return ResponseEntity.ok(lessonService.update(lessonCreateDto,lessonId));
  }
}
