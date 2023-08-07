package com.example.javaprojectspring.service;

import com.example.javaprojectspring.converter.OrderConverter;
import com.example.javaprojectspring.dto.OrderDto;
import com.example.javaprojectspring.dto.ProductDto;
import com.example.javaprojectspring.entity.Product;
import com.example.javaprojectspring.repository.OrderRepository;
import com.example.javaprojectspring.repository.ProductRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;


@Data
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ProductService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public List<ProductDto> get(int id){
        return productRepository.findAllByOrderId(id).stream()
                .map(product -> ProductDto.builder()
                        .id(product.getId())
                        .cost(product.getCost())
                        .name(product.getName())
                        .build())
                .toList();
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream()
                .map(product -> ProductDto.builder()
                        .id(product.getId())
                        .cost(product.getCost())
                        .name(product.getName())
                        .build())
                .toList();
    }

    public OrderDto addProduct(int orderId, ProductDto product) {
        productRepository.save(Product.builder()
                .name(product.getName())
                .cost(product.getCost())
                .orderId(orderId)
                .build());
        return orderRepository.findById(orderId)
                .map(o -> OrderConverter.toOrderDto(o, productRepository.findAllByOrderId(orderId)))
                .orElseThrow();
    }
}
