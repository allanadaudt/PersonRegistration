package com.person.Manager.controller;

import com.person.Manager.core.domain.entity.Person;
import com.person.Manager.core.usecase.person.FindPersonUseCase;
import com.person.Manager.core.usecase.person.InsertPersonUseCase;
import com.person.Manager.core.usecase.person.UpdatePersonUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    private final FindPersonUseCase findPersonUseCase;
    private final InsertPersonUseCase insertPersonUseCase;
    private final UpdatePersonUseCase updatePersonUseCase;

    public PersonController(FindPersonUseCase findPersonUseCase, InsertPersonUseCase insertPersonUseCase, UpdatePersonUseCase updatePersonUseCase) {
        this.findPersonUseCase = findPersonUseCase;
        this.insertPersonUseCase = insertPersonUseCase;
        this.updatePersonUseCase = updatePersonUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAllPeople() {
        return ResponseEntity.ok().body(findPersonUseCase.findAll());
    }

    @GetMapping(value = "/{name}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> findByName(@PathVariable("name") final String name) throws Exception {
        final Person foundPerson = findPersonUseCase.findByName(name);
        try {
            return ResponseEntity.ok(foundPerson);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> newPerson(@Valid @RequestBody final Person person) {
        final Person savedPerson = insertPersonUseCase.save(person);
        return ResponseEntity.created(URI.create(String.format("/person/"))).body(savedPerson);
    }

    @PatchMapping(consumes = APPLICATION_JSON_VALUE, path = "/{name}")
    public ResponseEntity<Person> updatePeople(@Valid @RequestBody final Person person, @PathVariable("name") final String name) throws Exception {
        final Person updatedPerson = updatePersonUseCase.updatePerson(person, name);
        return ResponseEntity.ok(updatedPerson);
    }
}
