package com.example.fastMangementSystem.entity.module;

import com.example.fastMangementSystem.entity.BaseEntity;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.List;

@Entity(name = "modules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleEntity extends BaseEntity {

    private String  moduleName;

    @ManyToOne(cascade = CascadeType.ALL)
    private CourseEntity course;
}