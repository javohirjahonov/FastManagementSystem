package com.example.fastMangementSystem.service;

import com.example.fastMangementSystem.dto.LoginDto;
import com.example.fastMangementSystem.dto.UserCreatedDto;
import com.example.fastMangementSystem.dto.response.JwtResponse;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.entity.user.UserRole;
import com.example.fastMangementSystem.exception.DataNotFoundException;
import com.example.fastMangementSystem.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserEntity save(UserCreatedDto userDto, List<UserRole> roles) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setRoles(roles);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    public JwtResponse login(LoginDto login) {
        UserEntity userEntity = userRepository.findUserEntityByEmail(login.getEmail())
                .orElseThrow(
                        () -> new DataNotFoundException("user not found")
                );

        if(passwordEncoder.matches(login.getPassword(), userEntity.getPassword())) {
            String accessToken = jwtService.generateAccessToken(userEntity);
            return JwtResponse.builder().accessToken(accessToken).build();
        }
        throw new DataNotFoundException("user not found");
    }
    public Optional<UserEntity> getById(UUID id){
       return Optional.ofNullable(userRepository.findById(id).orElseThrow(
               () -> new DataNotFoundException("user not found")));
    }
    public void deleteById(UUID id){
        userRepository.deleteById(id);
    }



}
