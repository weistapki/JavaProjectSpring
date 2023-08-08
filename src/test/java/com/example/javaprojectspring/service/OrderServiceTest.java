package com.example.javaprojectspring.service;

import com.example.javaprojectspring.dto.OrderDto;
import com.example.javaprojectspring.dto.ProductDto;
import com.example.javaprojectspring.entity.Order;
import com.example.javaprojectspring.entity.Product;
import com.example.javaprojectspring.repository.OrderRepository;
import com.example.javaprojectspring.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    private ProductRepository productRepository;
    private OrderService orderService;
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
        orderService = new OrderService(productRepository, orderRepository);
    }

    @Test
    public void getTest() {
        int orderId = 10;

        List<Product> products = List.of(
                Product.builder().name("Burger").cost(10).orderId(orderId).build(),
                Product.builder().name("Taco").cost(15).orderId(orderId).build(),
                Product.builder().name("Wrap").cost(5).orderId(orderId).build()
        );

        Order order = Order.builder().id(orderId).date(Date.valueOf(LocalDate.now())).build();

        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        when(productRepository.findAllByOrderId(anyInt())).thenReturn(products);

        OrderDto result = orderService.get(orderId);
        Assertions.assertEquals(30, result.getCost());
        Assertions.assertEquals(orderId, result.getId());
    }

    @Test
    public void getAllTest() {

        List<Order> orders = List.of(Order.builder().id(1).date(Date.valueOf(LocalDate.now())).build(),
                Order.builder().id(2).date(Date.valueOf(LocalDate.now())).build(),
                Order.builder().id(3).date(Date.valueOf(LocalDate.now())).build());

        List<Product> products_1 = List.of(
                Product.builder().name("Burger").cost(10).orderId(1).build(),
                Product.builder().name("Taco").cost(15).orderId(1).build()
        );
        List<Product> products_2 = List.of(
                Product.builder().name("Burger").cost(10).orderId(2).build(),
                Product.builder().name("Wrap").cost(5).orderId(2).build()
        );
        List<Product> products_3 = new ArrayList<>();

        when(orderRepository.findAll()).thenReturn(orders);
        when(productRepository.findAllByOrderId(1)).thenReturn(products_1);
        when(productRepository.findAllByOrderId(2)).thenReturn(products_2);
        when(productRepository.findAllByOrderId(3)).thenReturn(products_3);

        List<OrderDto> result = orderService.getAll();
        Assertions.assertEquals(25, result.get(0).getCost());
        Assertions.assertEquals(15, result.get(1).getCost());
        Assertions.assertEquals(0, result.get(2).getCost());
    }

    @Test
    public void addOrderTest() {
        int orderId = 10;

        List<Product> products = List.of(
                Product.builder().name("Burger").cost(10).orderId(orderId).build(),
                Product.builder().name("Taco").cost(15).orderId(orderId).build(),
                Product.builder().name("Wrap").cost(5).orderId(orderId).build()
        );

        List<ProductDto> productsDto = List.of(
                ProductDto.builder().id(1).name("Burger").cost(10).build(),
                ProductDto.builder().id(2).name("Taco").cost(15).build(),
                ProductDto.builder().id(3).name("Wrap").cost(5).build()
        );

        Order orderToSave = Order.builder().date(Date.valueOf(LocalDate.now())).build();
        Order orderToReturn = Order.builder().id(orderId).date(Date.valueOf(LocalDate.now())).build();
        OrderDto orderDto = OrderDto.builder().products(productsDto).build();

        when(orderRepository.save(orderToSave)).thenReturn(orderToReturn);
        when(productRepository.saveAll(products)).thenReturn(products);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderToReturn));
        when(productRepository.findAllByOrderId(orderId)).thenReturn(products);

        OrderDto result = orderService.addOrder(orderDto);
        Assertions.assertEquals(30, result.getCost());
        Assertions.assertEquals(orderId, result.getId());
    }
}
