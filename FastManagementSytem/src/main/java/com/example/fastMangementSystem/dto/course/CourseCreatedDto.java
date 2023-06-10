package com.example.fastMangementSystem.dto.course;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseCreatedDto {
    @NotEmpty(message = "not null")
    private String name;
    @NotEmpty(message = "not null")
    private String module;
}