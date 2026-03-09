package com.training.microservices.account.dto;

public record AccountResponse(
        Long CustomerId,
        String customerName,
        String email,
        Double balance,
        String accountType
) {}
