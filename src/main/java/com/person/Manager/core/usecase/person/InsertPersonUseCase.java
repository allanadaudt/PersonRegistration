package com.person.Manager.core.usecase.person;

import com.person.Manager.core.domain.entity.Person;
import com.person.Manager.core.domain.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class InsertPersonUseCase {
    private final PersonRepository personRepository;

    public InsertPersonUseCase(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public Person save(final Person person) {
        return personRepository.save(person);
    }
}
