package com.aoher.controller;

import com.aoher.model.Person;
import com.aoher.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class PeopleController {

    private PersonRepository repository;

    public PeopleController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/people")
    public List<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/people/{id}")
    public Person findById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping("/people")
    public Person save(@RequestBody Person person) {
        return repository.save(person);
    }

    @PutMapping("/people/{id}")
    public Person update(@PathVariable String id, @RequestBody Person person) {
        return repository.update(id, person);
    }

    @DeleteMapping("/people/{id}")
    public void delete(@PathVariable String id) {
        repository.delete(id);
    }
}
