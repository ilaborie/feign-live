package org.ilaborie.feign.client;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class JerseyCatClient implements CatClient {

  private final WebTarget target;

  public JerseyCatClient(String url) {
    super();
    Client client = ClientBuilder.newClient();
    target = client.target(url);
  }

  @Override
  public List<Cat> findAll() {
    return target.request()
        .get()
        .readEntity(new GenericType<List<Cat>>() {
        });
  }

  @Override
  public Cat findById(String id) {
    return target.path(id).request()
        .get()
        .readEntity(Cat.class);
  }

  @Override
  public Cat create(Cat newCat) {
    return target.request()
        .buildPost(Entity.entity(newCat, MediaType.APPLICATION_JSON_TYPE))
        .invoke().readEntity(Cat.class);
  }

  @Override
  public Cat delete(String id) {
    return target.path(id).request()
        .buildDelete()
        .invoke().readEntity(Cat.class);
  }
}
