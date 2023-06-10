package com.example.fastMangementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LessonCreateDto {
    private String name;
    private double price;
    private String lessonDuration;
    private String mentorName;
}
