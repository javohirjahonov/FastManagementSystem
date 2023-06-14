package com.example.fastMangementSystem.repository.card;

import com.example.fastMangementSystem.entity.card.CardEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {

    List<CardEntity> findOrderEntitiesByOwnerId(Pageable pageable, UUID userId);
}
