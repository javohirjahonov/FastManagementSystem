package com.example.fastMangementSystem.controller;

import com.example.fastMangementSystem.dto.groups.GroupsDto;
import com.example.fastMangementSystem.entity.groups.GroupEntity;
import com.example.fastMangementSystem.exception.RequestValidationException;
import com.example.fastMangementSystem.service.group.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize(value = "hasRole('ADMIN')")
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
    @PreAuthorize(value = "hasRole('ADMIN')")
    public void delete(
            @RequestParam UUID id
    ) {
        groupService.delete(id);
    }
    @PatchMapping("/{groupId}/update")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<GroupEntity> updateGroup(
            @RequestBody GroupsDto groupsDto,
            @PathVariable UUID groupId) {
        return ResponseEntity.ok(groupService.update(groupsDto,groupId));
    }
    @GetMapping("/get-user-groups")
    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('SUPER_ADMIN') " )
    public ResponseEntity<List<GroupEntity>> getUserGroups(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam UUID userId
    ){
        return ResponseEntity.ok(groupService.getUserGroups(size, page, userId));
    }
    @GetMapping("/get-all-groups")
    public ResponseEntity<List<GroupEntity>> getAll(
    ) {
        return ResponseEntity.ok( groupService.getAll());
    }

}
