package org.blackbergx9.customerhandler;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final Repository<Customer, UUID> repository;

    private final CrmProperties crmProperties;

    public CustomerController(Repository<Customer, UUID> repository, CrmProperties crmProperties) {
        this.repository = repository;
        this.crmProperties = crmProperties;
    }

    @GetMapping("/hi")
    public String hi() {
        return crmProperties.welcomeMessage()+" "+crmProperties.maxResults();
    }

    @GetMapping
    public Iterable<Customer> getAllCustomers() {


        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable UUID id) {

        return repository.findById(id);
    }

    @PostMapping
    @PutMapping
    public Customer saveCustomer(@RequestBody Customer customer) {

        return repository.save(customer);
    }

    @DeleteMapping("/{id}")
    public void removeCustomer(UUID id) {
        repository.deleteById(id);
    }
}
