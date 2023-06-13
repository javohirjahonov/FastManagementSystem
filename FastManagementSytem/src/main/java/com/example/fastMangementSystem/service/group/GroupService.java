package com.example.fastMangementSystem.service.group;

import com.example.fastMangementSystem.dto.groups.GroupsDto;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.groups.GroupEntity;
import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.course.CourseRepository;
import com.example.fastMangementSystem.repository.groups.GroupsRepository;
import com.example.fastMangementSystem.repository.user.UserRepository;
import com.example.fastMangementSystem.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService{

    private final GroupsRepository groupsRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public GroupEntity add(GroupsDto groupsDto, UUID mentorId, UUID courseId) {
        UserEntity mentor = userRepository.findById(mentorId)
                .orElseThrow(() -> new DataNotFoundException("This mentor not found"));

        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> new DataNotFoundException("This course not found"));

        GroupEntity groupEntity = modelMapper.map(groupsDto, GroupEntity.class);
        groupEntity.setMentorEntity(mentor);
        groupEntity.setCourse(course);
        groupsRepository.save(groupEntity);
        return groupEntity;
    }

    public GroupEntity update(GroupsDto groupsDto,UUID groupId) {
        GroupEntity groupEntity = (groupsRepository.findById(groupId)
                .orElseThrow(() -> new DataNotFoundException("group not found")));

        modelMapper.map(groupsDto, groupEntity);
        return groupsRepository.save(groupEntity);
    }

    public void delete(UUID id) {
        groupsRepository.deleteById(id);
    }

    public GroupEntity getById(UUID id) {
        return groupsRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Groups not found"));
    }

    public List<GroupEntity> getAll() {
        return groupsRepository.findAll();
    }


}
