package org.blackbergx9.customerhandler;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final Repository<Customer, UUID> repository;

    private final CrmProperties crmProperties;

    private final MessageSource  messageSource;

    public CustomerController(Repository<Customer, UUID> repository,
                              CrmProperties crmProperties, MessageSource messageSource) {
        this.repository = repository;
        this.crmProperties = crmProperties;
        this.messageSource = messageSource;
    }

    @GetMapping("/hi")
    public String hi() {
        return crmProperties.welcomeMessage()+" "+crmProperties.maxResults();
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name)
    {
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage("welcome.message", new Object[]{name}, locale);
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
