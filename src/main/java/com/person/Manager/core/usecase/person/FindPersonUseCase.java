package com.person.Manager.core.usecase.person;

import com.person.Manager.core.domain.entity.Person;
import com.person.Manager.core.domain.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FindPersonUseCase {
    private final PersonRepository personRepository;

    public FindPersonUseCase(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public List<Person> findAll() {
        return personRepository.findAll();
    }
    public Person findByName(final String name) throws Exception {
        Person person = personRepository.findByName(name);
        if (person == null) {
            throw new Exception();
        }
        return person;
    }
}
