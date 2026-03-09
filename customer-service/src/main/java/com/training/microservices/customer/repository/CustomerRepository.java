package com.training.microservices.customer.repository;

import com.training.microservices.customer.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CustomerRepository {
    private final Map<Long, Customer> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Customer save(Customer customer){
        Long id = idGenerator.getAndIncrement();
        customer.setId(id);
        store.put(id, customer);
        return customer;
    }

    public Customer findById(Long id){
        return store.get(id);
    }

    public boolean existsByEmail(String email){
        return store.values().stream()
                .anyMatch(c-> c.getEmail().equalsIgnoreCase(email));
    }


    public Customer getCustomerByEmail(String email){
        return store.values().stream()
                .filter(c-> c.getEmail().equalsIgnoreCase(email))
                .findAny().orElse(null);
    }

    public Collection<Customer> findAll() {
        return store.values();
    }

    public boolean deleteByEmail(String emailId){
        return store.values()
                .removeIf(c-> c.getEmail().equalsIgnoreCase(emailId));

    }

    public Customer update(Customer updatedCustomer) {

        store.put(updatedCustomer.getId(), updatedCustomer);

        return updatedCustomer;
    }

    public boolean deleteById(Long accountId) {
        return store.remove(accountId) != null;
    }
}
