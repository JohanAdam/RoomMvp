package io.johan.roommvp.services;

import io.johan.roommvp.models.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

  @GET("pokemon/")
  Call<Pokemon> getList();
//  Call<List<PokeList>> getList();

}
