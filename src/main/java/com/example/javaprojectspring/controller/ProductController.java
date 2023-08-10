package com.example.javaprojectspring.controller;

import com.example.javaprojectspring.dto.OrderDto;
import com.example.javaprojectspring.dto.ProductDto;
import com.example.javaprojectspring.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public List<ProductDto> get(@PathVariable int id) {
        return productService.get(id);
    }

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @PostMapping("/{id}")
    public OrderDto add(@PathVariable int id, @RequestBody ProductDto productDto) {
        return productService.addProduct(id, productDto);
    }
}
