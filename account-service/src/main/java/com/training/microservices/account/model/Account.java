package com.training.microservices.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Account {
    private Long accountId;
    private String customerId;
    private Double balance;
    private String accountType;
}
