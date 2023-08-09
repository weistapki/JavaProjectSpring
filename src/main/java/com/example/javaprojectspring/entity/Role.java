package com.example.javaprojectspring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "roles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {
    @Id
    private Long id;
    private String name;
}
