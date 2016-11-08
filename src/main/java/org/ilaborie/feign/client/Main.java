package org.ilaborie.feign.client;

import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.reactor.ReactorFeign;
import reactor.core.publisher.Flux;

public class Main {

    public static void main(String[] args) {
        String url = "http://localhost:8080/api/cats";

        // Get Client with CircuitBreaker
        CatClient client = ReactorFeign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .requestInterceptor(System.out::println)
                .target(CatClient.class, url);

        // Find All
        client.findAll()
                .flatMap(Flux::fromIterable)
                .map(Cat::getName)
                .subscribe(System.out::println);
    }

}
