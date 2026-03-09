package com.training.microservices.account.dto;

public record TransactionRequest(String customerId,
                                 Double amount, String accountType ) {
}
