package com.example.fastMangementSystem.service.module;

import com.example.fastMangementSystem.dto.ModuleCreateDto;
import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import com.example.fastMangementSystem.entity.module.ModuleEntity;
import com.example.fastMangementSystem.repository.module.ModuleRepository;
import com.example.fastMangementSystem.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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


    public ModuleEntity update(ModuleEntity moduleEntity) {
       return moduleRepository.save(moduleEntity);

    }


    public void delete(UUID id) {
   moduleRepository.deleteById(id);
    }


}
