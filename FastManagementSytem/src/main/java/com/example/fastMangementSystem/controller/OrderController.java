package com.example.fastMangementSystem.controller;

import com.example.fastMangementSystem.entity.order.OrderEntity;
import com.example.fastMangementSystem.service.UserService;
import com.example.fastMangementSystem.service.orderService.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('STUDENT')")
    public ResponseEntity<OrderEntity> addOrder(
            @RequestParam UUID courseId,
            @RequestParam UUID studentId
    ) {
        return ResponseEntity.ok(orderService.add(courseId, studentId));
    }


}
