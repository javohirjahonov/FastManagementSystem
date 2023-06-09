package com.example.fastMangementSystem.entity.course;

import com.example.fastMangementSystem.entity.BaseEntity;
import com.example.fastMangementSystem.entity.module.ModuleEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity userEntity;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ModuleEntity> modules;

}
