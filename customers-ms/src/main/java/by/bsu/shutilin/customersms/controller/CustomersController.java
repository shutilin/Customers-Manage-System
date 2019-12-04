package by.bsu.shutilin.customersms.controller;

import by.bsu.shutilin.customersms.model.Customer;
import by.bsu.shutilin.customersms.repository.CustomerTypesRepository;
import by.bsu.shutilin.customersms.repository.CustomersRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static by.bsu.shutilin.customersms.util.MetaphoneGenerator.getMetaphone;


@RestController
public class CustomersController {

    private static final Logger logger = LogManager.getLogger(CustomersController.class.getName());

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private CustomerTypesRepository customerTypesRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/customers")
    public Page<Customer> getCustomers(HttpServletRequest request, Pageable pageable) {

        logger.info("IP: " + request.getRemoteAddr() + " METHOD: getCustomers");
        return customersRepository.findAll(pageable);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/types/{typeId}/customers")
    public Page<Customer> getCustomersByType(HttpServletRequest request,
                                             @PathVariable(value = "typeId") Long typeId,
                                             Pageable pageable) {

        logger.info("IP: " + request.getRemoteAddr() + " METHOD: getCustomersByType");
        return customersRepository.findByCustomerTypeId(typeId, pageable);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/types/{typeId}/customers")
    public Customer createCustomer(HttpServletRequest request,
                                   @PathVariable(value = "typeId") Long typeId,
                                   @Valid @RequestBody Customer customers) {

        logger.info("IP: " + request.getRemoteAddr() + " METHOD: createCustomer");
        return customerTypesRepository.findById(typeId).map(customerType -> {
            customers.setCustomerType(customerType);
            return customersRepository.save(customers);
        }).orElseThrow(() -> new IllegalArgumentException("Type not found"));

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(HttpServletRequest request,
                               @PathVariable(value = "customerId") Long customerId) {

        logger.info("IP: " + request.getRemoteAddr() + " METHOD: deleteCustomer");
        customersRepository.deleteById(customerId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/types/{typeId}/customers/{customerId}")
    public Customer updateCustomer(HttpServletRequest request,
                                   @PathVariable(value = "customerId") Long customerId,
                                   @PathVariable(value = "typeId") Long typeId,
                                   @Valid @RequestBody Customer customer) {


        logger.info("IP: " + request.getRemoteAddr() + " METHOD: updateCustomer");
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


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/search/")
    public Page<Customer> getCustomersByFirstAndLastMetaphone(HttpServletRequest request,
                                                              @RequestParam(value = "firstName") String firstName,
                                                              @RequestParam(value = "lastName") String lastName,
                                                              Pageable pageable) {


        logger.info("IP: " + request.getRemoteAddr() + " METHOD: getCustomersByFirstAndLastMetaphone");
        return customersRepository.findByFirstnameAndLastNameMetaphone('%' + getMetaphone(firstName) + '%',
                '%' + getMetaphone(lastName) + '%', pageable);

    }

}
