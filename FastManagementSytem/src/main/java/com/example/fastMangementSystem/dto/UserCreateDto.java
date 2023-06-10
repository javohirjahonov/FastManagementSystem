package com.example.fastMangementSystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {
    private String name;
    private String phoneNumber;
    private String password;
}
