package com.aoher.repository;

import com.aoher.model.Person;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PersonRepository {

    private Map<String, Person> repo = new HashMap<>();

    public Person findById(String id) {
        return repo.get(id);
    }

    public List<Person> findAll() {
        return new ArrayList<>(repo.values());
    }

    public Person save(Person person) {
        String id = UUID.randomUUID().toString();
        person.setId(id);
        repo.put(id, person);
        return person;
    }

    public Person update(String id, Person person) {
        person.setId(id);
        repo.put(id, person);
        return person;
    }

    public void delete(String id) {
        repo.remove(id);
    }
}
