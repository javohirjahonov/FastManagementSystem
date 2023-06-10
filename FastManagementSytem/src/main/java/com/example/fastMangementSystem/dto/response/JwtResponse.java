package com.example.fastMangementSystem.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {
    private String accessToken;
}
