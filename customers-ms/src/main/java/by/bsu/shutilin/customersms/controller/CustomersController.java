package by.bsu.shutilin.customersms.controller;

import by.bsu.shutilin.customersms.model.Customers;
import by.bsu.shutilin.customersms.repository.CustomerTypesRepository;
import by.bsu.shutilin.customersms.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static by.bsu.shutilin.customersms.util.MetaphoneGenerator.getMetaphone;


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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/types/{typeId}/customers")
    public Page<Customers> getCustomersByType(@PathVariable(value = "typeId") Long typeId, Pageable pageable) {
        return customersRepository.findByCustomerTypeId(typeId, pageable);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/types/{typeId}/customers")
    public Customers createCustomer(@PathVariable(value = "typeId") Long typeId, @Valid @RequestBody Customers customers) {
        return customerTypesRepository.findById(typeId).map(customerType -> {
            customers.setCustomerType(customerType);
            return customersRepository.save(customers);
        }).orElseThrow(() -> new IllegalArgumentException("Not found"));

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable(value = "customerId") Long customerId) {
        customersRepository.deleteById(customerId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/types/{typeId}/customers/{customerId}")
    public Customers updateCustomer(@PathVariable(value = "customerId") Long customerId,
                                    @PathVariable(value = "typeId") Long typeId,
                                    @Valid @RequestBody Customers customer) {

        return customerTypesRepository.findById(typeId).map(customerType -> {
            customer.setCustomerType(customerType);
            return customersRepository.findById(customerId).map(customerFound -> {
                customerFound.setId(customerId);
                customerFound.setFirstName(customer.getFirstName());
                customerFound.setLastName(customer.getLastName());
                customerFound.setTitle(customer.getTitle());
                customerFound.setCustomerType(customer.getCustomerType());
                return customersRepository.save(customerFound);
            }).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        }).orElseThrow(() -> new IllegalArgumentException("Type found"));
    }

    @GetMapping("/search/{firstName}/{lastName}")
    public Page<Customers> getCustomersByFirstAndLastMetaphone(@PathVariable(value = "firstName") String firstName,
                                                               @PathVariable(value = "lastName") String lastName,
                                                               Pageable pageable) {

        return customersRepository.findByFirstnameAndLastNameMetaphone('%' + getMetaphone(firstName) + '%',
                '%' + getMetaphone(lastName) + '%', pageable);

    }

}
