package com.training.microservices.account.client;


import com.training.microservices.account.dto.CustomerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface CustomerClient {

    @GetExchange("/api/customers/{id}")
    CustomerResponse getCustomerById(@PathVariable("id") Long id);

}
