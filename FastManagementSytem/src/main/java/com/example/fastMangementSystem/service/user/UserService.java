package com.example.fastMangementSystem.service.user;

import com.example.fastMangementSystem.dto.JwtResponse;
import com.example.fastMangementSystem.dto.UserCreatedDto;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.service.BaseService;

import java.util.UUID;

public interface UserService extends BaseService<UserEntity, UserCreatedDto> {
    JwtResponse signIn(UserCreatedDto userCreatedDto);

    UserEntity signUp(String name, String phoneNumber, String password, UUID userId);
}
