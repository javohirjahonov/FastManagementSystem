package com.example.fastMangementSystem.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {
    @NotEmpty(message = "PhoneNumber not be empty")
    private String phoneNumber;

    @NotEmpty(message = "Password not be empty ")
    private String password;
}
