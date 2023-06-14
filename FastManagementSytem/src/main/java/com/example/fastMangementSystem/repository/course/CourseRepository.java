package com.example.fastMangementSystem.repository.course;

import com.example.fastMangementSystem.entity.course.CourseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    Optional<CourseEntity> getCourseEntityById(UUID id);
    List<CourseEntity> findCourseEntitiesBy(Pageable pageable, UUID userId);
}
