package com.example.javaprojectspring.controller;

import com.example.javaprojectspring.entity.Person;
import com.example.javaprojectspring.entity.Role;
import com.example.javaprojectspring.service.RegisterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final RegisterService registerService;
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }
    @PostMapping("/{id}/add-role")
    public Person addRoleToPerson(@PathVariable int id, @RequestBody Role role) {
        return registerService.addRoleToPerson(id, role);
    }
    @PostMapping
    public Person savePersonWithEncodedPassword(@RequestBody Person person) {
        return registerService.savePersonWithEncodedPassword(person);
    }
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable int id) {
        return registerService.getPersonById(id);
    }
}