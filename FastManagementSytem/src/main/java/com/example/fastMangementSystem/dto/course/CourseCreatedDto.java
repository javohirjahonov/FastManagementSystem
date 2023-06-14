package com.example.fastMangementSystem.dto.course;

import com.example.fastMangementSystem.entity.groups.GroupEntity;
import com.example.fastMangementSystem.entity.module.ModuleEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.Table;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseCreatedDto {
    @NotEmpty(message = "Course name not be null")
    private String name;
    @NotEmpty(message = "Course module not be null")
    private String module;
    private List<GroupEntity> groups;
}
