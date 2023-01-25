package com.person.Manager.controller;

import com.person.Manager.core.domain.entity.Address;
import com.person.Manager.core.usecase.adress.FindAdressUseCase;
import com.person.Manager.core.usecase.adress.InsertAdressUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
    private final FindAdressUseCase findAdressUseCase;
    private final InsertAdressUseCase insertAddressUseCase;

    public AddressController(FindAdressUseCase findAdressUseCase, InsertAdressUseCase insertAddressUseCase) {
        this.findAdressUseCase = findAdressUseCase;
        this.insertAddressUseCase = insertAddressUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Address>> findAllAdresses() {
        return ResponseEntity.ok().body(findAdressUseCase.findAll());
    }

    @GetMapping(value = "/{name}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Address>> findByName(@PathVariable String name) throws Exception {
        final List<Address> foundAdress = findAdressUseCase.findByAdressName(name);
        try {
            return ResponseEntity.ok(foundAdress);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/{name}")
    public ResponseEntity<List<Address>> newAdress(@Valid @RequestBody final List<Address> address, @PathVariable final String name) {
        final List<Address> savedAdress = insertAddressUseCase.insertAddress(address, name);
        return ResponseEntity.created(URI.create(String.format("/adress/"))).body(savedAdress);
    }

}
