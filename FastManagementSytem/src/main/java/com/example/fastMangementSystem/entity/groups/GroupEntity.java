package com.example.fastMangementSystem.entity.groups;

import com.example.fastMangementSystem.entity.BaseEntity;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupEntity extends BaseEntity {
    private String name;
    private String mentor;
    private String studentsAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity mentorEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    private CourseEntity course;

}
