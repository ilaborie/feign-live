package org.ilaborie.feign.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

@Headers("Content-Type: application/json")
public interface CatClient {

  @RequestLine("GET /")
  List<Cat> findAll();

  @RequestLine("GET /{id}")
  Cat findById(@Param("id") String id);

  @RequestLine("POST /")
  Cat create(Cat newCat);

  @RequestLine("DELETE /{id}")
  Cat delete(@Param("id") String id);
}
