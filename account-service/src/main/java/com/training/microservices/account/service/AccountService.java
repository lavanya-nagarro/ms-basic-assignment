package com.training.microservices.account.service;

import com.training.microservices.account.client.CustomerClient;
import com.training.microservices.account.dto.AccountResponse;
import com.training.microservices.account.dto.CustomerResponse;
import com.training.microservices.account.dto.TransactionRequest;
import com.training.microservices.account.exception.AccountNotFoundException;
import com.training.microservices.account.exception.CustomerNotFoundException;
import com.training.microservices.account.exception.InsufficientBalanceException;
import com.training.microservices.account.model.Account;
import com.training.microservices.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private  final CustomerClient customerClient;

    public AccountResponse addMoney(TransactionRequest request)  {

        CustomerResponse customer = getCustomer(Long.valueOf(request.customerId()));
        Account account = accountRepository.findById(request.customerId())
                .orElseGet(() -> createAccount(request, customer));
        account.setBalance(account.getBalance() + request.amount());
        accountRepository.save(account);
        return mapToResponse(account, customer);

    }

    public AccountResponse withdrawMoney(TransactionRequest request) {

        Account account = accountRepository.findById(request.customerId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        CustomerResponse customer = customerClient.getCustomerById(Long.valueOf(request.customerId()));
       if (account.getBalance() < request.amount()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - request.amount());
        accountRepository.save(account);
        return mapToResponse(account, customer);
    }

    private Account createAccount(TransactionRequest request, CustomerResponse customer) {
       Account account= new Account();
        account.setCustomerId(customer.id().toString());
        account.setAccountType(request.accountType());
        account.setBalance(BigDecimal.ZERO.doubleValue());
        return accountRepository.save(account);
    }

    public AccountResponse getAccountDetails(String accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account not found with  id: " + accountId));
        CustomerResponse customer = customerClient.getCustomerById(Long.valueOf(accountId));
        return mapToResponse(account, customer);
    }

    public void deleteAccount(String accountId) {
        boolean exists = accountRepository.existsById(accountId);
        if (!exists) {
            throw new AccountNotFoundException(
                    "Account not found with accountId: " + accountId
            );
        }
        accountRepository.deleteById(accountId);
    }

    private CustomerResponse getCustomer(Long customerId) {
        try {
            return customerClient.getCustomerById(customerId);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new CustomerNotFoundException("Customer does not exist");
        }
    }

    private AccountResponse mapToResponse(Account account, CustomerResponse customer) {
        return new AccountResponse(
                customer.id(),
                customer.name(),
                customer.email(),
                account.getBalance(),
                account.getAccountType()
        );
    }

}

