package com.example.javaprojectspring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDto {
    private Long id;
    private String username;
    private RoleDto role;
}
