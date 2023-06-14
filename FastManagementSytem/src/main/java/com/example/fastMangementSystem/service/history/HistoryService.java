package com.example.fastMangementSystem.service.history;

import com.example.fastMangementSystem.entity.history.HistoryEntity;
import com.example.fastMangementSystem.entity.order.OrderEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.history.HistoryRepository;
import com.example.fastMangementSystem.repository.orderRepository.OrderRepository;
import com.example.fastMangementSystem.repository.user.UserRepository;
import com.example.fastMangementSystem.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class HistoryService  {

    private final HistoryRepository historyRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public HistoryEntity add(UUID orderId, UUID userId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Order not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setOrderName(order.getName());
        historyEntity.setUser(user);
        historyRepository.save(historyEntity);
        return null;
    }

    public HistoryEntity update(HistoryEntity historyEntity) {
        return null;
    }

    public void delete(UUID id) {}

    public HistoryEntity getById(UUID id) {return null;}
}
