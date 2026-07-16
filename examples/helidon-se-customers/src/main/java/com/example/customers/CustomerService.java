package com.example.customers;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Domain service for customers. Handlers stay thin; business logic lives here.
 */
public final class CustomerService {

    private final Map<String, Customer> customers = new ConcurrentHashMap<>();

    public CustomerService() {
        // Seed sample data for a runnable example
        customers.put("1", new Customer("1", "Ada Lovelace", "ada@example.com"));
        customers.put("2", new Customer("2", "Grace Hopper", "grace@example.com"));
    }

    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(customers.get(id));
    }
}
