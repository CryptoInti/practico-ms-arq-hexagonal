package com.empresa.practica.infrastructure.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

//@Qualifier("mongoTemplateA")
@Configuration
public class MongoClientConnectionA {

    //BD A
    @Value("${spring.data.mongodb.databaseA.uri}")
    private String mongoAUri;

    @Primary
    @Bean
    public MongoClient mongoClientA() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoAUri))
                .build();
        return MongoClients.create(settings);
    }

    @Bean(name = "reactiveMongoTemplateA")
    public ReactiveMongoTemplate reactiveMongoTemplateA() {
        return new ReactiveMongoTemplate(mongoClientA(), getDatabaseNameFromAUri(mongoAUri));
    }

    private String getDatabaseNameFromAUri(String uri) {
        return new ConnectionString(uri).getDatabase();
    }

    //BD B
    @Value("${spring.data.mongodb.databaseB.uri}")
    private String mongoBUri;

    @Bean
    public MongoClient mongoClientB() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoBUri))
                .build();
        return MongoClients.create(settings);
    }

    @Bean(name = "reactiveMongoTemplateB")
    public ReactiveMongoTemplate reactiveMongoTemplateB() {
        return new ReactiveMongoTemplate(mongoClientB(), getDatabaseNameFromBUri(mongoBUri));
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClientB(), getDatabaseNameFromAUri(mongoAUri));
    }

    private String getDatabaseNameFromBUri(String uri) {
        return new ConnectionString(uri).getDatabase();
    }
}

