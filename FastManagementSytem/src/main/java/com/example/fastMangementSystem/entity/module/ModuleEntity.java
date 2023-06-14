package com.example.fastMangementSystem.entity.module;

import com.example.fastMangementSystem.entity.BaseEntity;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.groups.GroupEntity;
import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity(name = "modules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ModuleEntity extends BaseEntity {

    private String moduleName;

    @ManyToOne(cascade = CascadeType.ALL)
    private GroupEntity group;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LessonEntity> lessonEntities;
}
