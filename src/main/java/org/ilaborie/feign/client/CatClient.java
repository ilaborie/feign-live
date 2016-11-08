package org.ilaborie.feign.client;

import feign.Headers;
import feign.RequestLine;
import reactor.core.publisher.Mono;

import java.util.List;

@Headers("Content-Type: application/json")
public interface CatClient {

  @RequestLine("GET /")
  Mono<List<Cat>> findAll();

}
