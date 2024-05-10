package com.empresa.practica.infrastructure.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Qualifier("mongoTemplateB")
@Configuration
public class MongoClientConnectionB {
//    @Value("${spring.data.mongodb.databaseB.uri}")
//    private String mongoUri;
//
//    @Bean
//    public MongoClient mongoClientB() {
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(new ConnectionString(mongoUri))
//                .build();
//        return MongoClients.create(settings);
//    }
//
//    @Bean
//    public ReactiveMongoTemplate mongoTemplateB() {
//        return new ReactiveMongoTemplate(mongoClientB(), getDatabaseNameFromUri(mongoUri));
//    }
//
//    private String getDatabaseNameFromUri(String uri) {
//        return new ConnectionString(uri).getDatabase();
//    }
}
