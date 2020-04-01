package com.aoher.controller;

import com.aoher.model.Person;
import com.aoher.repository.PersonRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository repository;

    private static Person person;
    private static String personJsonString;

    private static final String JSON_PATH_ID = "$.id";
    private static final String JSON_PATH_NAME = "$.name";
    private static final String JSON_PATH_HEIGHT = "$.height";
    private static final String JSON_PATH_MASS = "$.mass";

    @BeforeAll
    static void setUp() {
        person = new Person("1", "Luke Skywalker", 172, 77);
        personJsonString = getJsonString(person);
    }

    @Test
    void findAll() throws Exception {
        List<Person> personList = Lists.newArrayList(
                person, new Person("2", "C-3PO", 167, 75)
        );

        when(repository.findAll()).thenReturn(personList);
        mockMvc.perform(get("/api/people"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(person.getId())))
                .andExpect(jsonPath("$.[0].name", is(person.getName())))
                .andExpect(jsonPath("$.[0].height", is(person.getHeight())))
                .andExpect(jsonPath("$.[0].mass", is(person.getMass())))
                .andExpect(jsonPath("$.[1].name", is(personList.get(1).getName())))
                .andExpect(jsonPath("$.[1].height", is(personList.get(1).getHeight())))
                .andExpect(jsonPath("$.[1].mass", is(personList.get(1).getMass())));
    }

    @Test
    void findById() throws Exception {
        when(repository.findById(person.getId())).thenReturn(person);
        mockMvc.perform(get("/api/people/" + person.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_ID, is(person.getId())))
                .andExpect(jsonPath(JSON_PATH_NAME, is(person.getName())))
                .andExpect(jsonPath(JSON_PATH_HEIGHT, is(person.getHeight())))
                .andExpect(jsonPath(JSON_PATH_MASS, is(person.getMass())));
    }

    @Test
    void testSave() throws Exception {
        when(repository.save(any(Person.class))).thenReturn(person);
        mockMvc.perform(post("/api/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personJsonString))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_ID, is(person.getId())))
                .andExpect(jsonPath(JSON_PATH_NAME, is(person.getName())))
                .andExpect(jsonPath(JSON_PATH_HEIGHT, is(person.getHeight())))
                .andExpect(jsonPath(JSON_PATH_MASS, is(person.getMass())));
    }

    @Test
    void testUpdate() throws Exception {
        when(repository.update(anyString(), any(Person.class))).thenReturn(person);
        mockMvc.perform(put("/api/people/" + person.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(personJsonString))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_ID, is(person.getId())))
                .andExpect(jsonPath(JSON_PATH_NAME, is(person.getName())))
                .andExpect(jsonPath(JSON_PATH_HEIGHT, is(person.getHeight())))
                .andExpect(jsonPath(JSON_PATH_MASS, is(person.getMass())));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/people/" + person.getId()))
                .andExpect(status().isOk());
    }

    private static String getJsonString(Person person) {
        return "{\"name\": \"" + person.getName()
                + "\",\"height\":" + person.getHeight()
                + ",\"mass\":" + person.getMass() + "}";
    }
}