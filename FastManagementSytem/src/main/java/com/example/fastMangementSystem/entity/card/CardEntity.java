package com.example.fastMangementSystem.entity.card;

import com.example.fastMangementSystem.entity.BaseEntity;
import com.example.fastMangementSystem.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardEntity extends BaseEntity {
    @Column(unique = true,nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String expireDate;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @Column(nullable = false)
    private Double balance;
    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity owner;
}
