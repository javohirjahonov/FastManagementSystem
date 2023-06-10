package com.example.fastMangementSystem.dto;

import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleCreateDto {
    private String  moduleName;


    private List<LessonEntity> course;

}
