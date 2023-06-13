package com.example.fastMangementSystem.service.lesson;

import com.example.fastMangementSystem.dto.LessonCreateDto;
import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import com.example.fastMangementSystem.entity.module.ModuleEntity;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.lesson.LessonRepository;
import com.example.fastMangementSystem.repository.course.CourseRepository;
import com.example.fastMangementSystem.repository.module.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;

    public LessonEntity add(LessonCreateDto lessonCreateDto, UUID courseId) {
        ModuleEntity module = moduleRepository.findById(courseId)
                .orElseThrow(() -> new DataNotFoundException("Course not found"));

        LessonEntity lessonEntity = modelMapper.map(lessonCreateDto, LessonEntity.class);
        lessonEntity.setLessonDuration(lessonCreateDto.getLessonDuration());
        lessonEntity.setModule(module);
        lessonRepository.save(lessonEntity);
        return lessonEntity;
    }


    public void delete(UUID id) {
        courseRepository.deleteById(id);
    }


    public LessonEntity update(LessonCreateDto lessonCreateDto, UUID lessonId) {
        LessonEntity lessonEntity = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new DataNotFoundException("Lesson not found"));

        modelMapper.map(lessonCreateDto, lessonEntity);
        return lessonRepository.save(lessonEntity);
    }

    public List<LessonEntity> getAll(int size, int page, UUID userId) {
//        Pageable pageable = PageRequest.of(page, size);
//        return lessonRepository.findLessonEntitiesByUserId(pageable, userId);
        return null;
    }
}
