package com.example.fastMangementSystem.dto;

import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleCreateDto {
    @NotEmpty(message = "ModuleName not be empty")
    private String  moduleName;

}
