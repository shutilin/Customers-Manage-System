package by.bsu.shutilin.customersms.controller;

import by.bsu.shutilin.customersms.model.CustomerTypes;
import by.bsu.shutilin.customersms.repository.CustomerTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CustomerTypeController {

    @Autowired
    private CustomerTypesRepository customerTypesRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/types")
    public Page<CustomerTypes> getTypes(Pageable pageable) {
        return customerTypesRepository.findAll(pageable);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/types/{typeId}")
    public Page<CustomerTypes> getTypeById(@PathVariable (value="typeId") Long typeId, Pageable pageable) {
       return customerTypesRepository.findCustomerTypesById(typeId,pageable);
    }

    @PostMapping("/types")
    public CustomerTypes createType(@Valid @RequestBody CustomerTypes customerTypes) {
        return customerTypesRepository.save(customerTypes);
    }
}
