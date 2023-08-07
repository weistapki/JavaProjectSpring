package com.example.javaprojectspring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("products")
public class Product implements Serializable {
    @Id
    private int id;
    private String name;
    private double cost;
    private int orderId;
}
