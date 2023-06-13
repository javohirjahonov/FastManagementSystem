package com.example.fastMangementSystem.service.course;

import com.example.fastMangementSystem.dto.course.CourseCreatedDto;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.course.CourseType;
import com.example.fastMangementSystem.entity.module.ModuleEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.course.CourseRepository;
import com.example.fastMangementSystem.repository.groups.GroupsRepository;
import com.example.fastMangementSystem.repository.module.ModuleRepository;
import com.example.fastMangementSystem.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ModuleRepository moduleRepository;
    private final GroupsRepository groupsRepository;
    private final ModelMapper modelMapper;

    public CourseEntity add(CourseCreatedDto courseCreatedDto, UUID adminId, UUID moduleId, String role) {
        CourseEntity courseEntity = modelMapper.map(courseCreatedDto, CourseEntity.class);

        ModuleEntity module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new DataNotFoundException("This module not found"));

        List<ModuleEntity> modules = new ArrayList<>();
        modules.add(module);
        courseEntity.setModules(modules);

        UserEntity admin = userRepository.findById(adminId)
                .orElseThrow(() -> new DataNotFoundException("This admin not found"));

        if (Objects.equals(role, "ONLINE")) {
            courseEntity.setCourseType(CourseType.ONLINE);
        } else if (Objects.equals(role, "OFFLINE")) {
            courseEntity.setCourseType(CourseType.OFFLINE);
        }

        courseEntity.setAdmin(admin);
        courseRepository.save(courseEntity);

        return courseEntity;
    }



    public void deleteById(UUID id) {
        courseRepository.deleteById(id);

    }

    public CourseEntity update(CourseCreatedDto update,UUID id) {
        CourseEntity courseEntity = (courseRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("course not found")));

        modelMapper.map(update, courseEntity);
        return courseRepository.save(courseEntity);
    }

    public Optional<CourseEntity> getById(UUID id) {
        CourseEntity contentEntity = courseRepository.getCourseEntityByCourseType(id).orElseThrow(()
                -> new IllegalArgumentException("course couldn't be found"));
        return Optional.ofNullable(contentEntity);
    }

    public List<CourseEntity> getUserCourse(int page, int size, UUID userId) {
//        Pageable pageable = PageRequest.of(page, size);
//        return courseRepository.findCourseEntitiesByUserEntityId(pageable, userId);
    return null;
    }

    public List<CourseEntity> getAllCourses() {
       return courseRepository.findAll();
    }



}
