package com.person.Manager.core.usecase.adress;

import com.person.Manager.core.domain.repository.AddressRepository;
import org.springframework.stereotype.Service;
@Service
public class DeleteAdressUseCase {

    private final AddressRepository addressRepository;

    public DeleteAdressUseCase(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    public void deleteAddresses(final Long peopleId) {
        addressRepository.deleteAllByPersonId(peopleId);
    }
}
