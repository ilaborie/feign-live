package org.ilaborie.feign.client;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;

import java.util.List;

import static java.time.Instant.now;

public class Main {

  public static void main(String[] args) {
    String url = "http://localhost:8080/api/cats";

    // Get Client
    CatClient client = Feign.builder()
        // Transform response body
        .decoder(new GsonDecoder())
        // Create request body
        .encoder(new GsonEncoder())
        // Add custom header
        .requestInterceptor(template -> template.header("Date", now().toString()))
        // Tips debug lite
        .requestInterceptor(System.out::println)
        // Tips debug logger
        .logLevel(Logger.Level.FULL)
        .logger(new Slf4jLogger())
        // Create instance
        .target(CatClient.class, url);

    // Find All
    List<Cat> cats = client.findAll();
    cats.forEach(System.out::println);
    System.out.println();

    // Find by id
    String id = cats.stream().map(Cat::getId).findFirst().orElse(null);
    Cat c = client.findById(id);
    System.out.println("Cat: " + c);
    System.out.println();

    // Create
    Cat newCat = new Cat("Felix", Cat.CatRace.Angora);
    Cat created = client.create(newCat);
    System.out.println("New Cat: " + created);
    System.out.println();

    // Delete
    Cat deleted = client.delete(created.getId());
    System.out.println("Deleted cat: " + deleted);
    System.out.println();
  }

}
