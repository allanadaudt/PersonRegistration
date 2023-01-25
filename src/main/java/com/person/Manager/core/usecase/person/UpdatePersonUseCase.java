package com.person.Manager.core.usecase.person;

import com.person.Manager.core.domain.entity.Person;
import com.person.Manager.core.domain.repository.PersonRepository;
import com.person.Manager.core.usecase.adress.DeleteAdressUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UpdatePersonUseCase {
    private final PersonRepository personRepository;
    private final FindPersonUseCase findPersonUseCase;
    private final DeleteAdressUseCase deleteAdressUseCase;

    public UpdatePersonUseCase(PersonRepository personRepository, FindPersonUseCase findPersonUseCase, DeleteAdressUseCase deleteAdressUseCase) {
        this.personRepository = personRepository;
        this.findPersonUseCase = findPersonUseCase;
        this.deleteAdressUseCase = deleteAdressUseCase;
    }
    @Transactional
    public Person updatePerson(final Person newPerson, final String name) throws Exception {
        Person person = findPersonUseCase.findByName(name);

        if (newPerson.getName() != null) {
            person.setName(newPerson.getName());
        }
        if (newPerson.getBirthDate() != null) {
            person.setBirthDate(newPerson.getBirthDate());
        }
        if (newPerson.getAddress() != null) {
            deleteAdressUseCase.deleteAddresses(person.getId());
            person.setAddress(newPerson.getAddress());
        }
        return personRepository.save(person);
    }
}
