package com.example.fastMangementSystem.controller;

import com.example.fastMangementSystem.dto.LoginDto;
import com.example.fastMangementSystem.dto.UserCreatedDto;
import com.example.fastMangementSystem.dto.response.JwtResponse;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.entity.user.UserRole;
import com.example.fastMangementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @RequestBody LoginDto login
    ) {
        return ResponseEntity.ok(userService.login(login));
    }
    @PostMapping("/admin/sign-up")
    public ResponseEntity<UserEntity> adminSignUp(
            @RequestBody UserCreatedDto userDto
    ) {
        return ResponseEntity.ok(userService.save(userDto, List.of(UserRole.ADMIN)));
    }

    @PostMapping("/client/sign-up")
    public ResponseEntity<UserEntity> userSignUp(
            @RequestBody UserCreatedDto userDto
    ) {
        return ResponseEntity.ok(userService.save(userDto, List.of(UserRole.CLIENT)));
    }
    @PostMapping("/mentor/sign-up")
    public ResponseEntity<UserEntity> mentorSignUp(
            @RequestBody UserCreatedDto userDto
    ) {
        return ResponseEntity.ok(userService.save(userDto, List.of(UserRole.MENTOR)));
    }
    @PostMapping("/operator/sign-up")
    public ResponseEntity<UserEntity> operatorSignUp(
            @RequestBody UserCreatedDto userDto
    ) {
        return ResponseEntity.ok(userService.save(userDto, List.of(UserRole.OPERATOR)));
    }



}
