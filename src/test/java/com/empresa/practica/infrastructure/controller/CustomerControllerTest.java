package com.empresa.practica.infrastructure.controller;

import com.empresa.practica.application.services.CustomerService;
import com.empresa.practica.domain.model.Customer;
import com.empresa.practica.infrastructure.controller.CustomerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer("1", "Test CREATE User", 1, false, true);
        when(customerService.createCustomer(any(Customer.class))).thenReturn(Mono.just(customer));

        Mono<ResponseEntity<Customer>> response = customerController.createCustomer(customer);

        StepVerifier.create(response)
                .expectNextMatches(savedCustomer -> savedCustomer.getStatusCode() == HttpStatus.CREATED)
                .verifyComplete();
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer("1", "Test GET User", 1, false, true);
        when(customerService.getCustomerById(any(String.class))).thenReturn(Mono.just(customer));

        Mono<ResponseEntity<Customer>> response = customerController.getCustomerById("1");

        StepVerifier.create(response)
                .expectNextMatches(foundCustomer -> foundCustomer.getStatusCode() == HttpStatus.OK)
                .verifyComplete();
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer("1", "Test UPDATE User", 1, false, true);
        when(customerService.updateCustomer(any(Customer.class))).thenReturn(Mono.just(customer));

        Mono<ResponseEntity<Customer>> response = customerController.updateCustomer("1", customer);

        StepVerifier.create(response)
                .expectNextMatches(updatedCustomer -> updatedCustomer.getStatusCode() == HttpStatus.OK)
                .verifyComplete();
    }

    @Test
    public void testDeleteCustomer() {
        when(customerService.deleteCustomer(any(String.class))).thenReturn(Mono.empty());

        Mono<ResponseEntity<Void>> response = customerController.deleteCustomer("1");

        StepVerifier.create(response)
                .expectNextMatches(deletedCustomer -> deletedCustomer.getStatusCode() == HttpStatus.NO_CONTENT)
                .verifyComplete();
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer1 = new Customer("1", "Test GET User", 1, false, true);
        Customer customer2 = new Customer("2", "Test ALL User", 111, true, false);
        when(customerService.getAllCustomers()).thenReturn(Flux.just(customer1, customer2));

        Flux<Customer> response = customerController.getAllCustomers();

        StepVerifier.create(response)
                .expectNext(customer1)
                .expectNext(customer2)
                .verifyComplete();
    }
}