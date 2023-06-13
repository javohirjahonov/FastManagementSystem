package com.example.fastMangementSystem.controller;

import com.example.fastMangementSystem.dto.LoginDto;
import com.example.fastMangementSystem.dto.UserCreateDto;
import com.example.fastMangementSystem.dto.response.JwtResponse;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.entity.user.UserRole;
import com.example.fastMangementSystem.exception.RequestValidationException;
import com.example.fastMangementSystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid  @RequestBody LoginDto login,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(userService.login(login));
    }
    @PostMapping("/admin/sign-up")
    public ResponseEntity<UserEntity> adminSignUp(
            @Valid @RequestBody UserCreateDto userDto,
            BindingResult bindingResult
    ) {


        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(userService.save(userDto, List.of(UserRole.ADMIN)));
    }

    @PostMapping("/client/sign-up")
    public ResponseEntity<UserEntity> userSignUp(
            @Valid @RequestBody UserCreateDto userDto,
            BindingResult bindingResult
    ) {


        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(userService.save(userDto, List.of(UserRole.CLIENT)));
    }
    @PostMapping("/mentor/sign-up")
    public ResponseEntity<UserEntity> mentorSignUp(
            @Valid @RequestBody UserCreateDto userDto,
            BindingResult bindingResult
    ) {


        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(userService.save(userDto, List.of(UserRole.MENTOR)));
    }
    @PostMapping("/operator/sign-up")
    public ResponseEntity<UserEntity> operatorSignUp(
            @Valid @RequestBody UserCreateDto userDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(userService.save(userDto, List.of(UserRole.OPERATOR)));
    }



}
