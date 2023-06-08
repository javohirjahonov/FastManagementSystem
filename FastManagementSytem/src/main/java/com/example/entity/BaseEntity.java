package com.example.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter

public class BaseEntity {
    @Id
    protected UUID id;

    @CreationTimestamp
    protected LocalDateTime created;

    @UpdateTimestamp
    protected  LocalDateTime updated;

}
