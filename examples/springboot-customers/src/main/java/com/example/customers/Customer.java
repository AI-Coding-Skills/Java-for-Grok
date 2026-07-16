package com.example.customers;

/**
 * API model for a customer. Prefer records/DTOs over exposing persistence entities.
 */
public record Customer(String id, String name, String email) {
}
