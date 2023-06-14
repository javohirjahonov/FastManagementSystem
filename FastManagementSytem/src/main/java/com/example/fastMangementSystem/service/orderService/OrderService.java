package com.example.fastMangementSystem.service.orderService;

import com.example.fastMangementSystem.entity.card.CardEntity;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.groups.GroupEntity;
import com.example.fastMangementSystem.entity.history.HistoryEntity;
import com.example.fastMangementSystem.entity.order.OrderEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.card.CardRepository;
import com.example.fastMangementSystem.repository.course.CourseRepository;
import com.example.fastMangementSystem.repository.history.HistoryRepository;
import com.example.fastMangementSystem.repository.orderRepository.OrderRepository;
import com.example.fastMangementSystem.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final HistoryRepository historyRepository;

    public OrderEntity add(UUID courseId, UUID studentId) {
        OrderEntity orderEntity = new OrderEntity();

        UserEntity student = userRepository.findById(studentId)
                .orElseThrow(() -> new DataNotFoundException("Student not found"));

        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> new DataNotFoundException("This course not found"));
        orderEntity.setName(course.getName());
        List<GroupEntity> groups = course.getGroups();
        for (GroupEntity group : groups) {
            orderEntity.setMentorName(group.getMentor());
            orderEntity.setCourseType(course.getCourseType());
            orderEntity.setStudent(student);
        }
        orderRepository.save(orderEntity);
        return orderEntity;
    }

    public OrderEntity update(UUID orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Order not found"));
        return orderRepository.save(order);
    }

    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }

    public OrderEntity getById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("This order not found"));
    }
    public List<OrderEntity> getUserOrders(int page, int size, UUID userId) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findOrderEntitiesBy(pageable, userId);
    }
    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }


}
