package com.person.Manager.core.domain.repository;

import com.person.Manager.core.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    void deleteAllByPersonId(Long personId);
}
