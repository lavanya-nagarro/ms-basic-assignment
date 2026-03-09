package com.training.microservices.customer.controller;

import com.training.microservices.customer.dto.CustomerRequest;
import com.training.microservices.customer.dto.CustomerResponse;
import com.training.microservices.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.addCustomer(customerRequest));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAllCustomers(){
        return customerService.getAllCustomers();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomer(@PathVariable Long id){

        return customerService.getCustomer(id);
    }

    @PutMapping
    public CustomerResponse updateCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.updateCustomer(customerRequest);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(
            @RequestParam Long id) {

        customerService.deleteCustomer(id);

        return ResponseEntity.ok("Customer deleted successfully");
    }
}
