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
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(productRepository, orderRepository);
    }

    @Test
    public void addProductTest() {
        int orderId = 10;

        List<ProductDto> productDto = List.of(ProductDto.builder().id(1).name("Burger").cost(5).build());
        List<Product> product = List.of(Product.builder().id(orderId).name("Burger").cost(5).orderId(orderId).build());
        Order order = Order.builder().id(orderId).date(Date.valueOf(LocalDate.now())).build();

        when(productRepository.save(product.get(0))).thenReturn(product.get(0));
        when(productRepository.findAllByOrderId(anyInt())).thenReturn(product);
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));

        OrderDto result = productService.addProduct(orderId, productDto.get(0));
        Assertions.assertEquals(5, result.getCost());
        Assertions.assertEquals(orderId, result.getId());
    }
}
