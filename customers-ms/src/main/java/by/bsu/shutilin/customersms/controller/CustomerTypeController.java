package by.bsu.shutilin.customersms.controller;

import by.bsu.shutilin.customersms.model.CustomerTypes;
import by.bsu.shutilin.customersms.repository.CustomerTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerTypeController {

    @Autowired
    private CustomerTypesRepository customerTypesRepository;

    @GetMapping("/types")
    public Page<CustomerTypes> getTypes(Pageable pageable) {
        return customerTypesRepository.findAll(pageable);
    }

    @PostMapping("/types")
    public CustomerTypes createType(@Valid @RequestBody CustomerTypes customerTypes) {
        return customerTypesRepository.save(customerTypes);
    }
}
