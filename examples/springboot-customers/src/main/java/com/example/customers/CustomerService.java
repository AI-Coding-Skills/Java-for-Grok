package com.example.customers;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Domain service for customers. Controllers stay thin; business logic lives here.
 */
@Service
public class CustomerService {

    private final Map<String, Customer> customers = new ConcurrentHashMap<>();

    public CustomerService() {
        customers.put("1", new Customer("1", "Ada Lovelace", "ada@example.com"));
        customers.put("2", new Customer("2", "Grace Hopper", "grace@example.com"));
    }

    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(customers.get(id));
    }

    public Customer create(CreateCustomerRequest request) {
        String id = UUID.randomUUID().toString();
        Customer customer = new Customer(id, request.name(), request.email());
        customers.put(id, customer);
        return customer;
    }
}
