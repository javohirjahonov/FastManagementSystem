package com.example.fastMangementSystem.repository.orderRepository;

import com.example.fastMangementSystem.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

}
