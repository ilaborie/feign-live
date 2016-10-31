package org.ilaborie.feign.client;

import feign.Headers;
import feign.RequestLine;

import java.util.List;

@Headers("Content-Type: application/json")
public interface CatClient {

  @RequestLine("GET /")
  List<Cat> findAll();

}
