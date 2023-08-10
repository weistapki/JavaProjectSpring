package com.example.javaprojectspring.service;

import com.example.javaprojectspring.entity.Person;
import com.example.javaprojectspring.entity.Role;
import com.example.javaprojectspring.repository.PersonRepository;
import com.example.javaprojectspring.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class RegisterService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public RegisterService(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Person getPersonById(int id) {
        return personRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }
    public Person addRoleToPerson(int id, Role role) {
        Person person = getPersonById(id);
        person.setRole(role);
        return personRepository.save(person);
    }
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }
    public Person savePersonWithEncodedPassword(Person person) {
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);
        return personRepository.save(person);
    }
}
