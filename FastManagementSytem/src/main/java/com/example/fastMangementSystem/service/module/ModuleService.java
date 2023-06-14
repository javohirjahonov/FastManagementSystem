package com.example.fastMangementSystem.service.module;

import com.example.fastMangementSystem.dto.ModuleCreateDto;
import com.example.fastMangementSystem.dto.course.CourseCreatedDto;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.groups.GroupEntity;
import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import com.example.fastMangementSystem.entity.module.ModuleEntity;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.course.CourseRepository;
import com.example.fastMangementSystem.repository.groups.GroupsRepository;
import com.example.fastMangementSystem.repository.lesson.LessonRepository;
import com.example.fastMangementSystem.repository.module.ModuleRepository;
import com.example.fastMangementSystem.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleService  {
    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final GroupsRepository groupsRepository;
    private final ModelMapper modelMapper;


    public ModuleEntity add(ModuleCreateDto moduleCreateDto, UUID groupId, UUID lessonId) {
        ModuleEntity moduleEntity = modelMapper.map(moduleCreateDto, ModuleEntity.class);

        GroupEntity groupEntity = groupsRepository.findById(groupId)
                .orElseThrow(() -> new DataNotFoundException("Group not found"));

        LessonEntity lessonEntity = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new DataNotFoundException("Lesson not found"));

        groupEntity.setModules(List.of(moduleEntity));
        moduleEntity.setLessonEntities(List.of(lessonEntity));

        moduleRepository.save(moduleEntity);
        return moduleEntity;
    }

    public List<LessonEntity> findLessonsInModule(UUID moduleId) {
        ModuleEntity moduleEntity = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new DataNotFoundException("Module not found"));

        return moduleEntity.getLessonEntities();
    }


    public ModuleEntity update(ModuleCreateDto update, UUID id) {
        ModuleEntity moduleEntity = moduleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("course not found"));

        modelMapper.map(update, moduleEntity);
        return moduleRepository.save(moduleEntity);
    }


    public void delete(UUID id) {
        moduleRepository.deleteById(id);
    }

    public List<ModuleEntity> getUserModule(int page, int size, UUID userId) {
        Pageable pageable = PageRequest.of(page, size);
        return moduleRepository.findModuleEntitiesBy(pageable, userId);
    }
    public List<ModuleEntity> getAll() {
        return moduleRepository.findAll();
    }

}
