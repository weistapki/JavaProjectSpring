package com.example.javaprojectspring.repository;


import com.example.javaprojectspring.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findAllByOrderId(int id);

    List<Product> findAll();

    @Override
    <S extends Product> Iterable<S> saveAll(Iterable<S> entities);

    Product save(Product product);

}
