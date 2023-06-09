package com.example.fastMangementSystem.service.user;

import com.example.fastMangementSystem.dto.JwtResponse;
import com.example.fastMangementSystem.dto.UserCreatedDto;
import com.example.fastMangementSystem.entity.user.UserEntity;
import com.example.fastMangementSystem.exceptions.AlreadyHasUserException;
import com.example.fastMangementSystem.exceptions.DataNotFoundException;
import com.example.fastMangementSystem.exceptions.UserPasswordCheckException;
import com.example.fastMangementSystem.repository.user.UserRepository;
import com.example.fastMangementSystem.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @Override
    public UserEntity add(UserCreatedDto userCreatedDto) {
        UserEntity userEntity = modelMapper.map(userCreatedDto, UserEntity.class);
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public UserEntity getById(UUID id) {
        return null;
    }

    @Override
    public JwtResponse signIn(UserCreatedDto userCreatedDto) {
        UserEntity userEntity = userRepository.findUserEntitiesByPhoneNumber(userCreatedDto.getPhoneNumber())
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        if (userEntity.getPassword().equals(userCreatedDto.getPassword())) {
            String accessToken = jwtService.generateAccessToken(userEntity);
            return JwtResponse.builder().accessToken(accessToken).build();
        }
        throw new UserPasswordCheckException("Password is wrong");
    }

    @Override
    public UserEntity signUp(String name, String phoneNumber, String password, UUID userId) {
        UserEntity user = userRepository.findUserEntitiesById(userId)
                .orElseThrow(() -> new AlreadyHasUserException("user not found"));
        if (Objects.equals(user.getPhoneNumber(), phoneNumber)) {
            throw new AlreadyHasUserException("This user already registered ");
        }
        return user;
    }

}
