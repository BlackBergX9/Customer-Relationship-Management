package org.blackbergx9.customerhandler;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.UUID;

@Configuration
@Profile("!prod")   // Maybe we only want to load our dummy data when we are not in Production
public class CustomerConfiguration {

    @Bean
    ApplicationListener<ApplicationReadyEvent>
    customerAppReady(Repository<Customer, UUID> customerRepository ) {

        return event -> {

            customerRepository.save(new Customer("John", "john@example.com", "1-800-PHONE"));
            customerRepository.save(new Customer("Jane", "jane@example.com", "2-453-PHONE"));
            customerRepository.save(new Customer("Jack", "jack@example.com", "3-658-PHONE"));

            customerRepository.findAll().forEach(System.out::println);

        };
    }
}
