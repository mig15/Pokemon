package com.android.developer.www.pokemon.retrofit;

import com.android.developer.www.pokemon.data.PokemonRepository;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonApi {

    @GET("pokemon")
    Call<PokemonRepository> getPokemonsList(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<ResponseBody> getStringResponse(@Path("id") int id);
}
