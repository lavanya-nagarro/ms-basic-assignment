package com.training.microservices.customer.service;

import com.training.microservices.customer.client.AccountClient;
import com.training.microservices.customer.dto.CustomerRequest;
import com.training.microservices.customer.dto.CustomerResponse;
import com.training.microservices.customer.exception.AccountNotFoundException;
import com.training.microservices.customer.exception.CustomerNotFoundException;
import com.training.microservices.customer.exception.DuplicateCustomerException;
import com.training.microservices.customer.model.Customer;
import com.training.microservices.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountClient accountClient;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        Customer customer = Customer.builder()
                .name(customerRequest.name())
                .phone(customerRequest.phone())
                .email(customerRequest.email())
                .address(customerRequest.address()).build();
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new DuplicateCustomerException("Email already exists");
        }
        customerRepository.save(customer);
        log.info("Customer Created Successfully");
        return new CustomerResponse(customer.getId(), customer.getName(),
                customer.getEmail(), customer.getPhone(), customer.getAddress());

    }

    public CustomerResponse getCustomer(Long id) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return new CustomerResponse(customer.getId(),customer.getName(),customer.getEmail(), customer.getPhone(), customer.getAddress());

    }

     public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail()
                        , customer.getPhone(), customer.getAddress())).toList();
    }

    public void deleteCustomer(Long id) {


        boolean deleted = customerRepository.deleteById(id);

        if (!deleted) {
            throw new CustomerNotFoundException(
                    "Customer not found with customer id: " + id
            );
        }
        try {
            accountClient.deleteAccount(id);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new AccountNotFoundException("Account does not exist for customer id"+ id);
        }
    }

    public CustomerResponse getCustomerByEmail(String emailId) {
        Customer customer=customerRepository.getCustomerByEmail(emailId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return new CustomerResponse(customer.getId(),customer.getName(),customer.getEmail(), customer.getPhone(), customer.getAddress());
    }

    public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
        Customer existing =
                customerRepository.getCustomerByEmail(customerRequest.email());

        if (existing == null) {
            throw new CustomerNotFoundException(
                    "Customer not found with email: " + customerRequest.email()
            );
        }

        existing.setName(customerRequest.name());
        existing.setPhone(customerRequest.phone());
        existing.setAddress(customerRequest.address());

        Customer updated =
                customerRepository.update(existing);

        return new CustomerResponse(
                updated.getId(),
                updated.getName(),
                updated.getEmail(),
                updated.getPhone(),
                updated.getAddress()
        );
    }
}

