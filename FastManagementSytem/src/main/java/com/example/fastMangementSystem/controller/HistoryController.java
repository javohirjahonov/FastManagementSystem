package com.example.fastMangementSystem.controller;

import com.example.fastMangementSystem.entity.history.HistoryEntity;
import com.example.fastMangementSystem.entity.order.OrderEntity;
import com.example.fastMangementSystem.service.history.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('STUDENT')")
    public ResponseEntity<HistoryEntity> addHistory(
            @RequestParam UUID orderId,
            @RequestParam UUID userId
    ) {
        return ResponseEntity.ok(historyService.add(orderId, userId));
    }

    @GetMapping("/buy")
    @PreAuthorize(value = "hasRole('STUDENT')")
    public ResponseEntity<HistoryEntity> buyOrder(
            @RequestParam UUID orderId,
            @RequestParam UUID cardId
    ) {
        return ResponseEntity.ok(historyService.buyOrder(orderId, cardId));
    }
}
