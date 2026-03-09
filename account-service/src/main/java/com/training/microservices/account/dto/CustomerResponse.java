package com.training.microservices.account.dto;

public record CustomerResponse(Long id, String name, String email,
                               String phone, String address) {
}
