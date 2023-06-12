package com.example.fastMangementSystem.dto.groups;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class GroupsDto {

    @NotEmpty(message = "groupsName not be empty")
    private String name;

    @NotEmpty(message = "mentorsName not be empty")
    private String mentorName;

    @NotEmpty(message = "studentsAmount not be empty")
    private String studentsAmount;

}
