package org.ilaborie.feign.client;

import java.util.List;

public interface CatClient {

  List<Cat> findAll();

  Cat findById(String id);

  Cat create(Cat newCat);

  Cat delete(String id);
}
