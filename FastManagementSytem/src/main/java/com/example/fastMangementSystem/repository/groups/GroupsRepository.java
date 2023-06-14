package com.example.fastMangementSystem.repository.groups;

import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.groups.GroupEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GroupsRepository extends JpaRepository<GroupEntity, UUID> {
    List<GroupEntity> findGroupEntitiesBy(Pageable pageable, UUID userId);
}
