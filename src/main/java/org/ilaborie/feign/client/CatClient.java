package org.ilaborie.feign.client;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CatClient {

  @GET("/api/cats")
  Call<List<Cat>> findAll();

  @GET("/api/cats/{id}")
  Call<Cat> findById(@Path("id") String id);

  @POST("/api/cats")
  Call<Cat> create(@Body Cat newCat);

  @DELETE("/api/cats/{id}")
  Call<Cat> delete(@Path("id") String id);
}
