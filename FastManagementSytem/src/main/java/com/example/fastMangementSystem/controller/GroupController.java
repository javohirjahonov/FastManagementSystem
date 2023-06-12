package com.example.fastMangementSystem.controller;

import com.example.fastMangementSystem.dto.groups.GroupsDto;
import com.example.fastMangementSystem.entity.groups.GroupEntity;
import com.example.fastMangementSystem.repository.groups.GroupsRepository;
import com.example.fastMangementSystem.service.group.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor

public class GroupController {

    private final GroupService groupService;

    @PostMapping("/add")
    public ResponseEntity<GroupEntity> addGroup(
            @RequestBody GroupsDto groupsDto,
            @RequestParam UUID mentorId
    ) {
        return ResponseEntity.ok(groupService.add(groupsDto, mentorId));
    }

}
