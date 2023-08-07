package com.example.javaprojectspring.converter;


import com.example.javaprojectspring.dto.OrderDto;
import com.example.javaprojectspring.dto.ProductDto;
import com.example.javaprojectspring.entity.Order;
import com.example.javaprojectspring.entity.Product;

import java.sql.Date;
import java.util.List;

public class OrderConverter {

    public static OrderDto toOrderDto(Order order, List<Product> products) {
        double orderCost = products.stream()
                .mapToDouble(Product::getCost)
                .sum();
        List<ProductDto> productsDto = products.stream()
                .map(p -> ProductDto.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .cost(p.getCost())
                        .build())
                .toList();
        return OrderDto.builder()
                .id(order.getId())
                .date((Date) order.getDate())
                .cost(orderCost)
                .products(productsDto)
                .build();
    }
}