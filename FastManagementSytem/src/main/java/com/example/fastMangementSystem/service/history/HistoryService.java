package com.example.fastMangementSystem.service.history;

import com.example.fastMangementSystem.entity.card.CardEntity;
import com.example.fastMangementSystem.entity.history.HistoryEntity;
import com.example.fastMangementSystem.entity.order.OrderEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.card.CardRepository;
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
    private final CardRepository cardRepository;

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

    public HistoryEntity buyOrder(UUID orderId, UUID cardId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("This order not found"));
        CardEntity cardEntity = cardRepository.findById(cardId)
                .orElseThrow(() -> new DataNotFoundException("This card not found"));

        HistoryEntity history = new HistoryEntity();
        history.setOrderName(order.getName());
        historyRepository.save(history);
        return history;
    }
}
