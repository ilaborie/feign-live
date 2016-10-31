package org.ilaborie.feign.client;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class Main {

  public static void main(String[] args) {
    String url = "http://localhost:8080/api/cats";

    // Get Client with CircuitBreaker
    CatClient client = Feign.builder()
        .decoder(new GsonDecoder())
        .encoder(new GsonEncoder())
        .requestInterceptor(System.out::println)
        .target(CatClient.class, url);

    // Find All
    client.findAll()
        .forEach(System.out::println);
  }

}
