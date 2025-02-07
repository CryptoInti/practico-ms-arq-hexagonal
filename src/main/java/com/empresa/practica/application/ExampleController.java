package com.empresa.practica.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ExampleController {

    @GetMapping("/hello")
    public Mono<String> sayHello() {
        return Mono.just("Hello, world!");
    }
}