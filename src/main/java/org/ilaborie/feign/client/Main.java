package org.ilaborie.feign.client;

import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.hystrix.HystrixFeign;
import rx.Observable;

public class Main {

  public static void main(String[] args) {
    String url = "http://localhost:8080/api/cats";

    // Get Client with CircuitBreaker
    CatClient client = HystrixFeign.builder()
        .decoder(new GsonDecoder())
        .encoder(new GsonEncoder())
        .requestInterceptor(System.out::println)
        .target(CatClient.class, url);

    // Find All
    client.findAll()
        .flatMap(Observable::from)
        .map(Cat::getName)
        .toBlocking()
        .forEach(System.out::println);
  }
}
