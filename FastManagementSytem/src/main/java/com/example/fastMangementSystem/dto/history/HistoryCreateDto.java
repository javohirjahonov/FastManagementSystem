package com.example.fastMangementSystem.dto.history;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryCreateDto {
    @NotEmpty(message = "Order name not be null")
    private String orderName;
    @NotEmpty(message = "Order price not be null")
    private Double orderPrice;
}
