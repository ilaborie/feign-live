package org.ilaborie.feign.client;

import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.hystrix.HystrixFeign;
import rx.Observable;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) {
    String url = "http://localhost:8080/api/cats";

    // Fallback implementation
    CatClient fallback = () -> {
      Cat cat = new Cat("Chuck Norris's cat", Cat.CatRace.Sphynx);
      return Observable.just(Collections.singletonList(cat));
    };

    // Get Client with CircuitBreaker
    CatClient client = HystrixFeign.builder()
        .decoder(new GsonDecoder())
        .encoder(new GsonEncoder())
        .requestInterceptor(System.out::println)
        .target(CatClient.class, url, fallback);

    // Find All every 2 seconds
    Observable.interval(2, TimeUnit.SECONDS)
        .flatMap(i -> client.findAll())
        .forEach(System.out::println);

    waiting();
  }

  private static void waiting() {
    try {
      Thread.sleep(TimeUnit.MINUTES.toMillis(5));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
