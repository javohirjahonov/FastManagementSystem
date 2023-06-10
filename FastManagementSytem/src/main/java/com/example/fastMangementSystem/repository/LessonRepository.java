package com.example.fastMangementSystem.repository;

import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {

}
