package com.empresa.practica.application.services;

import com.empresa.practica.domain.excrptions.CustomerServiceException;
import com.empresa.practica.domain.model.Customer;
import com.empresa.practica.domain.repository.CustomerRepository;
//import com.empresa.practica.domain.repository.EntityARepository;
//import com.empresa.practica.domain.repository.EntityBRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

//    private final ReactiveMongoTemplate reactiveMongoTemplateA;
    private final ReactiveMongoTemplate reactiveMongoTemplateB;
    private final CustomerRepository customerRepository;

    //Drools
//    private final KieSession kieSession;

    public CustomerService(
            @Qualifier("reactiveMongoTemplateB") ReactiveMongoTemplate reactiveMongoTemplateB,
            CustomerRepository customerRepository) {
        this.reactiveMongoTemplateB = reactiveMongoTemplateB;
        this.customerRepository = customerRepository;
//        this.kieSession = kieSession;
    }

    // Create a new Customer OLD sin Drools
    public Mono<Customer> createCustomer(Customer customer) {
        if (customer == null) {
            throw new CustomerServiceException("Customer no puede ser null");
        }
        System.out.println("customer es"+customer.toString());

        if (customer.isLoyal()) {
            System.out.println("Guardando en la base de datos B");
            return reactiveMongoTemplateB.save(customer, "customers")
                    .switchIfEmpty(Mono.error(new CustomerServiceException("Error al crear al Customer en BD B")));
        } else {
            System.out.println("Guardando en la base de datos A");

            return customerRepository.save(customer)
                    .switchIfEmpty(Mono.error(new CustomerServiceException("Error al crear al Customer en BD A")));
        }
    }

    // Create a new Customer con Drools No funciono
//    public Mono<Customer> createCustomer(Customer customer) {
//        kieSession.insert(customer);
//        kieSession.fireAllRules();
//        return Mono.just(customer);
//    }

    // Retrieve a Customer by its ID
    public Mono<Customer> getCustomerById(String id) {
        if (id == null || id.isEmpty()) {
            throw new CustomerServiceException("ID no puede ser vacio");
        }
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomerServiceException("Customer no encontrado, id: " + id)));
    }

    // Update an existing Customer
    public Mono<Customer> updateCustomer(Customer customer) {
        if (customer == null) {
            throw new CustomerServiceException("Customer no puede ser null");
        }
        return customerRepository.save(customer);
    }

    // Delete a Customer by its ID
    public Mono<Void> deleteCustomer(String id) {
        if (id == null || id.isEmpty()) {
            throw new CustomerServiceException("ID no puede ser vacio o null");
        }
        return customerRepository.deleteById(id);
    }

    // Retrieve all Customers
    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Flux<Customer> findAllFromMongoTemplateB() {
        return reactiveMongoTemplateB.findAll(Customer.class, "customers");
    }
//
//    public void saveCustomer(Customer customer) {
//        if (customer.isLoyal()) {
//            saveToDatabaseA(customer);
//        } else if (customer.isNew()) {
//            saveToDatabaseB(customer);
//        }
//    }
//
//    private Mono<EntityA> saveToDatabaseA(Customer customer) {
//        // Lógica para guardar en la base de datos A
//        EntityA entityA = new EntityA();
//        entityA.setNombre(customer.getName());
//        return entityARepository.save(entityA);
//    }
//
//    private Mono<EntityB> saveToDatabaseB(Customer customer) {
//        // Lógica para guardar en la base de datos B
//        EntityB entityB = new EntityB();
//        entityB.setNombre(customer.getName());
//        return entityBRepository.save(entityB);
//    }
}
