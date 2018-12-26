package io.johan.roommvp.contants;

import io.johan.roommvp.models.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface NetworkResponseHandler extends Callback<Pokemon> {

  @Override
  void onResponse(Call<Pokemon> call, Response<Pokemon> response);
//  void onResponse(Call<String> call, Response<String> response);
//  void onResponse(Call<List<PokeList>> call, Response<List<PokeList>> response);

  @Override
  void onFailure(Call<Pokemon> call, Throwable t);
//  void onFailure(Call<List<PokeList>> call, Throwable t);
//  void onFailure(Call<List<PokeList>> call, Throwable t);
}

