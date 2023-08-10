package com.example.javaprojectspring.controller;

import com.example.javaprojectspring.dto.OrderDto;
import com.example.javaprojectspring.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public OrderDto get(@PathVariable int id) {
        return orderService.get(id);
    }

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public OrderDto add(@RequestBody OrderDto orderDto) {
        return orderService.addOrder(orderDto);
    }
}
