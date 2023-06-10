package com.example.fastMangementSystem.service.lesson;

import com.example.fastMangementSystem.dto.LessonCreateDto;
import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import com.example.fastMangementSystem.entity.module.ModuleEntity;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.course.CourseRepository;
import com.example.fastMangementSystem.repository.module.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class LessonServiceImpl {

    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;

    public LessonEntity add(LessonCreateDto lessonCreateDto, UUID courseId) {
        ModuleEntity module = moduleRepository.findById(courseId)
                .orElseThrow(() -> new DataNotFoundException("Course not found"));

        LessonEntity lessonEntity = modelMapper.map(lessonCreateDto, LessonEntity.class);
        lessonEntity.setName(lessonCreateDto.getName());
        lessonEntity.setPrice(lessonCreateDto.getPrice());
        lessonEntity.setMentorName(lessonCreateDto.getMentorName());
        lessonEntity.setLessonDuration(lessonCreateDto.getLessonDuration());
        lessonEntity.setModule(module);
        return lessonEntity;
    }


    public LessonEntity update(LessonEntity lessonEntity) {
        return null;
    }


    public void delete(UUID id) {

    }


    public LessonEntity getById(UUID id) {
        return null;
    }
}
