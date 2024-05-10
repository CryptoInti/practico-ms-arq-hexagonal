package com.empresa.practica.application.services;

import com.empresa.practica.domain.model.Customer;
import com.empresa.practica.domain.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer("1", "Test CREATE User", 1, false, true);
        when(customerRepository.save(any(Customer.class))).thenReturn(Mono.just(customer));

        Mono<Customer> response = customerService.createCustomer(customer);

        StepVerifier.create(response)
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer("1", "Test GET User", 1, false, true);
        when(customerRepository.findById(any(String.class))).thenReturn(Mono.just(customer));

        Mono<Customer> response = customerService.getCustomerById("1");

        StepVerifier.create(response)
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer("1", "Test UPDATE User", 1, false, true);
        when(customerRepository.save(any(Customer.class))).thenReturn(Mono.just(customer));

        Mono<Customer> response = customerService.updateCustomer(customer);

        StepVerifier.create(response)
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    public void testDeleteCustomer() {
        when(customerRepository.deleteById(any(String.class))).thenReturn(Mono.empty());

        Mono<Void> response = customerService.deleteCustomer("1");

        StepVerifier.create(response)
                .verifyComplete();
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer1 = new Customer("1", "Test GET User", 1, false, true);
        Customer customer2 = new Customer("2", "Test ALL User", 111, true, false);
        when(customerRepository.findAll()).thenReturn(Flux.just(customer1, customer2));

        Flux<Customer> response = customerService.getAllCustomers();

        StepVerifier.create(response)
                .expectNext(customer1)
                .expectNext(customer2)
                .verifyComplete();
    }
}