package com.example.fastMangementSystem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {

    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotEmpty(message = " PhoneNumber must not be empty and only 7 numbers ")
    @Pattern(regexp = "^\\d{9}$")
    private String phoneNumber;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).+$")
    @NotEmpty(message = "Password must not be empty")
    private String password;
}
