package com.training.microservices.customer.dto;

public record CustomerRequest(Long id, String name, String email,
                               String phone, String address) {
}