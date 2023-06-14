package com.example.fastMangementSystem.repository.history;

import com.example.fastMangementSystem.entity.history.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoryRepository extends JpaRepository<HistoryEntity, UUID> {
}
