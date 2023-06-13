package com.example.fastMangementSystem.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LessonCreateDto {

    @NotEmpty(message = "Lesson Name not be null")
    private String name;

    @NotEmpty(message = "Lesson Price not be null")
    private double price;

    @NotEmpty(message = "Lesson lessonDuration not be null")
    private String lessonDuration;

}
