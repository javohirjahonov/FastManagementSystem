package com.example.fastMangementSystem.service.card;


import com.example.fastMangementSystem.dto.LessonCreateDto;
import com.example.fastMangementSystem.dto.card.CardCreatedDto;
import com.example.fastMangementSystem.entity.card.CardEntity;
import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import com.example.fastMangementSystem.entity.order.OrderEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.card.CardRepository;
import com.example.fastMangementSystem.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public CardEntity add(CardCreatedDto card,UUID studentId) {

        UserEntity student = userRepository.findById(studentId)
                .orElseThrow(() -> new DataNotFoundException("This student not found"));

        CardEntity cardEntity = modelMapper.map(card, CardEntity.class);
        cardEntity.setOwner(student);
        cardRepository.save(cardEntity);
        return cardEntity;
    }





    public CardEntity update( CardCreatedDto update, UUID cardId) {
        CardEntity card = cardRepository.findById(cardId)
                .orElseThrow(() -> new DataNotFoundException("Card not found"));
            modelMapper.map(update,card);
        return cardRepository.save(card);
    }

    public void delete(UUID id) {
        cardRepository.deleteById(id);
    }

    public CardEntity getById(UUID id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("This card not found"));

    }
    public List<CardEntity> getUserCards(int page, int size, UUID userId) {
        Pageable pageable = PageRequest.of(page, size);
        return cardRepository.findOrderEntitiesByOwnerId(pageable, userId);
    }
    public List<CardEntity> getAll() {
        return cardRepository.findAll();
    }


}
