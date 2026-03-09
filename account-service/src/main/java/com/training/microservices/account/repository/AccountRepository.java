package com.training.microservices.account.repository;

import com.training.microservices.account.model.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepository {

    // Thread-safe Map
    private final Map<String, Account> storeAccountInfo = new ConcurrentHashMap<>();

    public Account save(Account account) {

        storeAccountInfo.put(account.getCustomerId(), account);

        return account;
    }

    public Optional<Account> findById(String customerId) {

        return Optional.ofNullable(storeAccountInfo.get(customerId));
    }

    public boolean existsById(String customerId) {

        return storeAccountInfo.containsKey(customerId);
    }

    public void deleteById(String customerId) {

        storeAccountInfo.remove(customerId);
    }

    public List<Account> findAll() {

        return new ArrayList<>(storeAccountInfo.values());
    }
}
