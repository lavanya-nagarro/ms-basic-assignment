package com.training.microservices.account.controller;

import com.training.microservices.account.service.AccountService;
import com.training.microservices.account.dto.AccountResponse;
import com.training.microservices.account.dto.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/withdraw")
    public AccountResponse withdraw(@RequestBody TransactionRequest request) {
        return accountService.withdrawMoney(request);
    }

    @PostMapping("/deposit")
    public AccountResponse deposit(@RequestBody TransactionRequest request) {
        return accountService.addMoney(request);
    }

    @GetMapping("/{accountId}")
    public AccountResponse getAccountDetails(@PathVariable String accountId) {
        return accountService.getAccountDetails(accountId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
         accountService.deleteAccount(id);
        return "Customer deleted successfully";

    }
}
