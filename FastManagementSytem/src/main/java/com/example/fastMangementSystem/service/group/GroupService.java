package com.example.fastMangementSystem.service.group;

import com.example.fastMangementSystem.dto.groups.GroupsDto;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.groups.GroupEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.groups.GroupsRepository;
import com.example.fastMangementSystem.repository.user.UserRepository;
import com.example.fastMangementSystem.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService{

    private final GroupsRepository groupsRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public GroupEntity add(GroupsDto groupsDto, UUID userId) {
        UserEntity mentor = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("This user not found"));

        GroupEntity groupEntity = modelMapper.map(groupsDto, GroupEntity.class);
        groupEntity.setMentorEntity(mentor);
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
        GroupEntity groupEntity = groupsRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Groups not found"));
        return groupEntity;
    }


}
