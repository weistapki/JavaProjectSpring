package com.example.javaprojectspring.repository;


import com.example.javaprojectspring.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    Optional<Order> findById(int id);

    List<Order> findAll();

    Order save(Order order);

}
