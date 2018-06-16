package by.bsu.shutilin.customersms.controller;

import by.bsu.shutilin.customersms.model.Customers;
import by.bsu.shutilin.customersms.repository.CustomerTypesRepository;
import by.bsu.shutilin.customersms.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import javax.validation.Valid;

@RestController
public class CustomersController {

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private CustomerTypesRepository customerTypesRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/customers")
    public Page<Customers> getCustomers(Pageable pageable) {
        return customersRepository.findAll(pageable);
    }

    @GetMapping("/types/{typeId}/customers")
    public Page<Customers> getCustomersByType(@PathVariable (value="typeId") Long typeId, Pageable pageable) {
        return customersRepository.findByCustomerTypeId(typeId,pageable);
    }

    @PostMapping("/types/{typeId}/customers")
    public Customers createCustomer(@PathVariable (value="typeId") Long typeId, @Valid @RequestBody Customers customers) {
        return customerTypesRepository.findById(typeId).map(customerType -> {
            customers.setCustomerType(customerType);
            return customersRepository.save(customers);
        }).orElseThrow(() -> new IllegalArgumentException("Not found"));

    }

}
