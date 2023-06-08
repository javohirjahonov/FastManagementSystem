package com.example.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreatedDto {
    @NotEmpty(message = "can not be empty")
    private String name;

    @NotEmpty(message = "can not be empty")
    @Column(unique = true, name = "this email already exists")
    @Email(message = "Invalid email address")
    private String email;

    @NotEmpty(message = "can not be empty")
    @Column(unique = true, name = "this phoneNumber already exists")
    @Pattern(regexp = "^\\d+${7}")
    private String phoneNumber;

    @NotEmpty(message = "can not be empty")
    @NotBlank(message = "password not null")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$")
    // Abc123parlX --> example parol

    private String password;


}
