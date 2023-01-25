package com.person.Manager.core.usecase.adress;

import com.person.Manager.core.domain.entity.Address;
import com.person.Manager.core.domain.entity.Person;
import com.person.Manager.core.domain.repository.AddressRepository;
import com.person.Manager.core.domain.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InsertAdressUseCase {
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public InsertAdressUseCase(final PersonRepository personRepository, final AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }
    public List<Address> insertAddress(final List<Address> addressList, final String name) {
        Person person = personRepository.findByName(name);
        List<Address> newAddressList = person.getAddress();
        newAddressList.addAll(addressList);

        addressRepository.saveAll(newAddressList);
        personRepository.save(person);

        return addressList;
    }
}
