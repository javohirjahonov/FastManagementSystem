package com.example.fastMangementSystem.controller;

import com.example.fastMangementSystem.dto.ModuleCreateDto;
import com.example.fastMangementSystem.entity.module.ModuleEntity;
import com.example.fastMangementSystem.service.module.ModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/module/v1")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<ModuleEntity> add(
            @RequestParam UUID lessonId,
            @RequestParam UUID groupId,
            @Valid @RequestBody ModuleCreateDto moduleCreateDto
    ){
        return ResponseEntity.ok(moduleService.add(moduleCreateDto, groupId, lessonId));
    }

    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public void delete(
            @RequestParam UUID id
    ){
        moduleService.delete(id);
    }
    @GetMapping("/get-user-modules")
    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<List<ModuleEntity>> getAll(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam UUID userId
    ){
        return ResponseEntity.ok(moduleService.getUserModule(size, page, userId));
    }
    @PatchMapping("/{moduleId}/update")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<ModuleEntity> updateCourse(
            @RequestBody ModuleCreateDto moduleCreateDto,
            @PathVariable UUID moduleId) {
        return ResponseEntity.ok(moduleService.update(moduleCreateDto, moduleId));
    }
    @GetMapping("/get-all-modules")
    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('MENTOR')")
    public ResponseEntity<List<ModuleEntity>> getAll(
    ) {
        return ResponseEntity.ok( moduleService.getAll());
    }
}
