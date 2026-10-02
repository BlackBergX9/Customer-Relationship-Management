package org.blackbergx9.customerhandler;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CustomerRepository implements Repository<Customer, UUID>{

    private Map<UUID, Customer> customers = new ConcurrentHashMap<>();

    @Override
    public Customer save(Customer entity) {

        if (entity.id() == null)
            entity = new Customer(UUID.randomUUID(), entity.name(), entity.email(), entity.phone());

        customers.put(entity.id(), entity);

        return entity;
    }

    @Override
    public Customer findById(UUID id) {

        return customers.get(id);
    }

    @Override
    public Iterable<Customer> findAll() {

        return customers.values();
    }

    @Override
    public void deleteById(UUID id) {

        customers.remove(id);
    }
}
