package com.example.javaprojectspring.service;

import com.example.javaprojectspring.converter.OrderConverter;
import com.example.javaprojectspring.dto.OrderDto;
import com.example.javaprojectspring.entity.Order;
import com.example.javaprojectspring.entity.Product;
import com.example.javaprojectspring.repository.OrderRepository;
import com.example.javaprojectspring.repository.ProductRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Data
@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public OrderDto get(int id) {
        return orderRepository.findById(id)
                .map(order -> OrderConverter.toOrderDto(order, productRepository.findAllByOrderId(id)))
                .orElseThrow();
    }

    public List<OrderDto> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> OrderConverter.toOrderDto(order,productRepository.findAllByOrderId(order.getId())))
                .toList();
    }

    public OrderDto addOrder(OrderDto orderDto) {
        Order order = orderRepository.save(Order.builder().date(Date.valueOf(LocalDate.now())).build());
        if (orderDto.getProducts() != null) {
            List<Product> products = orderDto.getProducts().stream()
                    .map(product -> Product.builder()
                            .name(product.getName())
                            .cost(product.getCost())
                            .orderId(order.getId())
                            .build())
                    .toList();
            productRepository.saveAll(products);
        }
        return orderRepository.findById(order.getId())
                .map(o -> OrderConverter.toOrderDto(o, productRepository.findAllByOrderId(order.getId())))
                .orElseThrow();
    }
}
