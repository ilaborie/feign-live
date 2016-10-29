package org.ilaborie.feign.client;

import feign.Headers;
import feign.RequestLine;
import rx.Observable;

import java.util.List;

@Headers("Content-Type: application/json")
public interface CatClient {

  @RequestLine("GET /")
  Observable<List<Cat>> findAll();

}
