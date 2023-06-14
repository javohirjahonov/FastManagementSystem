package com.example.fastMangementSystem.controller;

import com.example.fastMangementSystem.entity.order.OrderEntity;
import com.example.fastMangementSystem.service.UserService;
import com.example.fastMangementSystem.service.card.CardService;
import com.example.fastMangementSystem.service.history.HistoryService;
import com.example.fastMangementSystem.service.orderService.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final HistoryService historyService;
    private final CardService cardService;

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<OrderEntity> addOrder(
            @RequestParam UUID courseId,
            @RequestParam UUID studentId
    ) {
        return ResponseEntity.ok(orderService.add(courseId, studentId));
    }

    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('STUDENT')")
    public void delete(
            @RequestParam UUID id
    ){
        orderService.delete(id);
    }
    @GetMapping("/get-user-orders")
    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<List<OrderEntity>> getAll(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam UUID userId
    ){
        return ResponseEntity.ok(orderService.getUserOrders(size, page, userId));
    }
    @GetMapping("/get-all-orders")
    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<List<OrderEntity>> getAll(
    ) {
        return ResponseEntity.ok( orderService.getAll());
    }



}
