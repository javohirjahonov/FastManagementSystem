package com.example.fastMangementSystem.entity.groups;

import com.example.fastMangementSystem.entity.BaseEntity;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.module.ModuleEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    private List<ModuleEntity> modules;

}
