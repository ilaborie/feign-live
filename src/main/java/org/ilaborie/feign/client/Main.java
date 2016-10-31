package org.ilaborie.feign.client;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    String url = "http://localhost:8080/api/cats";

    CatClient client = new JerseyCatClient(url);

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
