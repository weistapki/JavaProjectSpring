package com.example.javaprojectspring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "persons")
public class Person {
    @Id
    private Long id;
    private String username;
    private String password;
    @MappedCollection(idColumn = "id")
    private Role role;
}




