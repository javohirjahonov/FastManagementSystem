package com.example.fastMangementSystem.service.orderService;

import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.groups.GroupEntity;
import com.example.fastMangementSystem.entity.order.OrderEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.course.CourseRepository;
import com.example.fastMangementSystem.repository.orderRepository.OrderRepository;
import com.example.fastMangementSystem.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public OrderEntity add(OrderEntity orderEntity, UUID courseId, UUID studentId) {

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

    public OrderEntity update(OrderEntity orderEntity) {
        return null;
    }

    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }

    public OrderEntity getById(UUID id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("This order not found"));
        return order;
    }
}
