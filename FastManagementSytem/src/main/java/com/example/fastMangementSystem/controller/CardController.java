package com.example.fastMangementSystem.controller;

import com.example.fastMangementSystem.dto.LessonCreateDto;
import com.example.fastMangementSystem.dto.card.CardCreatedDto;
import com.example.fastMangementSystem.entity.card.CardEntity;
import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import com.example.fastMangementSystem.repository.card.CardRepository;
import com.example.fastMangementSystem.service.card.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/card/v1")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('STUDENT')")
    public ResponseEntity<CardEntity> add(
          @Valid @RequestBody CardCreatedDto cardCreatedDto,
            @RequestParam UUID studentId
    ){
        return ResponseEntity.ok(cardService.add(cardCreatedDto,studentId));
    }

    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasRole('STUDENT')")
    public void delete(
            @RequestParam UUID id
    ){
        cardService.delete(id);
    }



    @GetMapping("/get-student-cards")
    @PreAuthorize(value = "hasRole('STUDENT')")
    public ResponseEntity<List<CardEntity>> getUserLessons(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam UUID userId
    ){
        return ResponseEntity.ok(cardService.getUserCards(size,page,userId));
    }


    @PatchMapping("/{cardId}/update")
    @PreAuthorize(value = "hasRole('STUDENT')")
    public ResponseEntity<CardEntity> updateLesson(
            @RequestBody CardCreatedDto cardCreateDto,
            @PathVariable UUID cardId) {
        return ResponseEntity.ok(cardService.update(cardCreateDto,cardId));
    }
    @GetMapping("/get-all-cards")
    @PreAuthorize(value = "hasRole('STUDENT') or hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<List<CardEntity>> getAll(
    ) {
        return ResponseEntity.ok( cardService.getAll());
    }
}
