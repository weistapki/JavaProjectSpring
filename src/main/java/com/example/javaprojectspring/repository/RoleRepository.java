package com.example.javaprojectspring.repository;

import com.example.javaprojectspring.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer> {
    Role findByName(String name);
}
