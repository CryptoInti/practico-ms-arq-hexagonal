package com.empresa.practica.infrastructure.controller;

import com.empresa.practica.application.services.CustomerService;
import com.empresa.practica.domain.excrptions.CustomerServiceException;
import com.empresa.practica.domain.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Create a new Customer
    @PostMapping
    public Mono<ResponseEntity<Customer>> createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer)
                .map(savedCustomer -> new ResponseEntity<>(savedCustomer, HttpStatus.CREATED));
    }

    // Retrieve a Customer by its ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Customer>> getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id)
                .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK));
    }

    // Update an existing Customer
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        if (!id.equals(customer.getCustomerId())) {
            return Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
        return customerService.updateCustomer(customer)
                .map(updatedCustomer -> new ResponseEntity<>(updatedCustomer, HttpStatus.OK));
    }

    // Delete a Customer by its ID
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable String id) {
        return customerService.deleteCustomer(id)
                .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
    }

    // Retrieve all Customers
    @GetMapping
    public Flux<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Retrieve all Customers from B
    @GetMapping("/B")
    public Flux<Customer> findAllFromMongoTemplateB() {
        return customerService.findAllFromMongoTemplateB();
    }

    @ExceptionHandler(CustomerServiceException.class)
    public ResponseEntity<String> handleCustomerServiceException(CustomerServiceException ex) {
        // Personaliza tu respuesta aquí
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}