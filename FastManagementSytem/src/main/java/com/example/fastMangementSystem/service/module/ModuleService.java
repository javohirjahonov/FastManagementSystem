package com.example.fastMangementSystem.service.module;

import com.example.fastMangementSystem.dto.ModuleCreateDto;
import com.example.fastMangementSystem.dto.course.CourseCreatedDto;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import com.example.fastMangementSystem.entity.module.ModuleEntity;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.module.ModuleRepository;
import com.example.fastMangementSystem.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleService  {
    private final ModuleRepository moduleRepository;
    private final ModelMapper modelMapper;


    public ModuleEntity add(ModuleCreateDto moduleCreateDto, List<LessonEntity> lessonEntities) {
        ModuleEntity map = modelMapper.map(moduleCreateDto, ModuleEntity.class);
        map.setLessonEntities(lessonEntities);
        return moduleRepository.save(map);
    }


    public ModuleEntity update(ModuleCreateDto update, UUID id) {
        ModuleEntity moduleEntity = (moduleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("course not found")));
        if (!update.getModuleName().isEmpty()){
            moduleEntity.setModuleName(update.getModuleName());
        }
        if (!update.getCourses().isEmpty()){
            moduleEntity.setCourse((CourseEntity) update.getCourses());
        }
//        CourseEntity map = modelMapper.map(update, CourseEntity.class);
        return moduleRepository.save(moduleEntity);
    }


    public void delete(UUID id) {
   moduleRepository.deleteById(id);
    }

    public List<ModuleEntity> getUserModule(int page, int size, UUID userId) {
        Pageable pageable = PageRequest.of(page, size);
        return moduleRepository.findModuleEntitiesByUserEntityId(pageable, userId);
    }

}
