package com.example.fastMangementSystem.entity.lesson;

import com.example.fastMangementSystem.entity.BaseEntity;
import com.example.fastMangementSystem.entity.module.ModuleEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonEntity extends BaseEntity {
    private String name;
    private double price;
    private String lessonDuration;
}
