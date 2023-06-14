package com.example.fastMangementSystem.entity.course;

import com.example.fastMangementSystem.entity.BaseEntity;
import com.example.fastMangementSystem.entity.groups.GroupEntity;
import com.example.fastMangementSystem.entity.module.ModuleEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseEntity extends BaseEntity {

    private String name;
    private String module;
    private CourseType courseType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<GroupEntity> groups;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ModuleEntity> modules;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity admin;

}
