package com.example.fastMangementSystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreatedDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
