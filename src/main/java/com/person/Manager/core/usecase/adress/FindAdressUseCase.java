package com.person.Manager.core.usecase.adress;

import com.person.Manager.core.domain.entity.Address;
import com.person.Manager.core.domain.entity.Person;
import com.person.Manager.core.domain.repository.AddressRepository;
import com.person.Manager.core.domain.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FindAdressUseCase {
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public FindAdressUseCase(final AddressRepository addressRepository, final PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
    public List<Address> findByAdressName(final String name) throws Exception {
        Person person = personRepository.findByName(name);
        if (person == null) {
            throw new Exception();
        }
        return person.getAddress();
    }
}
