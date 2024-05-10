package com.empresa.practica.infrastructure.configuration;

import com.empresa.practica.domain.repository.CustomerRepository;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class DroolsConfiguration {

    private final ReactiveMongoTemplate reactiveMongoTemplateB;
    private final CustomerRepository customerRepository;

    public DroolsConfiguration(
            @Qualifier("reactiveMongoTemplateB") ReactiveMongoTemplate reactiveMongoTemplateB,
            CustomerRepository customerRepository) {
        this.reactiveMongoTemplateB = reactiveMongoTemplateB;
        this.customerRepository = customerRepository;
    }

//    @Bean
//    public KieContainer kieContainer() {
//        KieServices kieServices = KieServices.Factory.get();
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        kieFileSystem.write(ResourceFactory.newClassPathResource("rules.drl"));
//        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
//        kieBuilder.buildAll();
//        KieModule kieModule = kieBuilder.getKieModule();
//        return kieServices.newKieContainer(kieModule.getReleaseId());
//    }

//    @Bean
//    public KieSession kieSession() {
//        KieContainer kieContainer = kieContainer();
//        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
//        kieSession.setGlobal("reactiveMongoTemplateB", reactiveMongoTemplateB);
//        kieSession.setGlobal("customerRepository", customerRepository);
//        return kieSession;
//    }
}

