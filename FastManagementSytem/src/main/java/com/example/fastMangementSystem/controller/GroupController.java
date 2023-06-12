package com.example.fastMangementSystem.controller;

import com.example.fastMangementSystem.dto.LessonCreateDto;
import com.example.fastMangementSystem.dto.groups.GroupsDto;
import com.example.fastMangementSystem.entity.groups.GroupEntity;
import com.example.fastMangementSystem.entity.lesson.LessonEntity;
import com.example.fastMangementSystem.exception.RequestValidationException;
import com.example.fastMangementSystem.repository.groups.GroupsRepository;
import com.example.fastMangementSystem.service.group.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor

public class GroupController {

    private final GroupService groupService;

    @PostMapping("/add")
    public ResponseEntity<GroupEntity> addGroup(
            @RequestBody GroupsDto groupsDto,
            @RequestParam UUID mentorId,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(groupService.add(groupsDto, mentorId));
    }

    @DeleteMapping("/delete")
    public void delete(
            @RequestParam UUID id
    ) {
        groupService.delete(id);
    }
    @PatchMapping("/{groupId}/update")
    public ResponseEntity<GroupEntity> updateGroup(
            @RequestBody GroupsDto groupsDto,
            @PathVariable UUID groupId) {
        return ResponseEntity.ok(groupService.update(groupsDto,groupId));
    }

}
