package com.example.fastMangementSystem.repository.module;

import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.module.ModuleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity, UUID> {
    List<ModuleEntity> findModuleEntitiesBy(Pageable pageable, UUID userId);
}
