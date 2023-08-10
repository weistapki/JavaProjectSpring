package com.example.javaprojectspring.repository;

import com.example.javaprojectspring.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Integer> {
    Person findByUsername(String username);
}
