package org.ilaborie.feign.client;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

public class Main {

  public static void main(String[] args) {
    String url = "http://localhost:8080/";

    // Get Client
    CatClient client = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CatClient.class);

    // Find All
    List<Cat> cats = executeCall(() -> client.findAll());
    cats.forEach(System.out::println);
    System.out.println();

    // Find by id
    String id = cats.stream().map(Cat::getId).findFirst().orElse(null);
    Cat c = executeCall(() -> client.findById(id));
    System.out.println("Cat: " + c);
    System.out.println();

    // Create
    Cat newCat = new Cat("Felix", Cat.CatRace.Angora);
    Cat created = executeCall(() -> client.create(newCat));
    System.out.println("New Cat: " + created);
    System.out.println();

    // Delete
    Cat deleted = executeCall(() -> client.delete(created.getId()));
    System.out.println("Deleted cat: " + deleted);
    System.out.println();
  }

  private static <T> T executeCall(Supplier<Call<T>> supplier) {
    try {
      Call<T> call = supplier.get();
      return call.execute().body();
    } catch (IOException e) {
      return null;
    }
  }

}
