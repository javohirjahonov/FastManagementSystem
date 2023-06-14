package com.example.fastMangementSystem.dto.card;

import com.example.fastMangementSystem.entity.card.CardType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardCreatedDto {
    @NotEmpty(message = "Card number not be null")
    @Pattern(regexp = "^\\d{16}$\n")
    private String cardNumber;
    @NotEmpty(message = "Card password not be null")
    @Pattern(regexp = "^\\d{4}$\n")
    private String password;
    @NotEmpty(message = "Card expire date not be null")
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/\\d{2}$\n")
    private String expireDate;
    @NotEmpty(message = "Card type not be null")
    private CardType cardType;
    @NotEmpty(message = "Card balance not be null")
    @Max(value = 3000000, message = "Balance cannot exceed 3000000")
    private Double balance;
}
