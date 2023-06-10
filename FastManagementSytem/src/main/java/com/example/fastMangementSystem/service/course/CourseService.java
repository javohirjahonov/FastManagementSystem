package com.example.fastMangementSystem.service.course;

import com.example.fastMangementSystem.dto.course.CourseCreatedDto;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.course.CourseType;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.course.CourseRepository;
import com.example.fastMangementSystem.repository.user.UserRepository;
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
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CourseEntity add(CourseCreatedDto courseCreatedDto, UUID userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("user not found"));

        CourseEntity courseEntity = modelMapper.map(courseCreatedDto, CourseEntity.class);
        courseEntity.setUserEntity(user);

        courseEntity.setCourseType(CourseType.valueOf(String.join("-", courseEntity.getName(), courseEntity.getModule())));
        courseEntity.setCourseType(CourseType.OFFLINE);
        courseRepository.save(courseEntity);
        return courseEntity;
    }

    public void deleteById(UUID id) {
        courseRepository.deleteById(id);

    }

    public CourseEntity update(CourseCreatedDto update,UUID id) {
        CourseEntity courseEntity = (courseRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("course not found")));
        if (!update.getModule().isEmpty()){
            courseEntity.setModule(update.getModule());
        }
        if (!update.getName().isEmpty()){
            courseEntity.setName(update.getName());
        }
//        CourseEntity map = modelMapper.map(update, CourseEntity.class);
        return courseRepository.save(courseEntity);
    }

    public Optional<CourseEntity> getById(UUID id) {
        CourseEntity contentEntity = courseRepository.getCourseEntityByCourseType(id).orElseThrow(()
                -> new IllegalArgumentException("course couldn't be found"));
        return Optional.ofNullable(contentEntity);
    }

    public List<CourseEntity> getUserCourse(int page, int size, UUID userId) {
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.findCourseEntitiesByUserListId(pageable, userId);
    }


}