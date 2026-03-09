package com.training.microservices.customer.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
public interface AccountClient {


    @DeleteExchange("/api/account/{id}")
    String deleteAccount(@PathVariable Long id);
}
