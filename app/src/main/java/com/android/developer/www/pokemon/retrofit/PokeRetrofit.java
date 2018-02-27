package com.android.developer.www.pokemon.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeRetrofit {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    private static final PokeRetrofit ourInstance = new PokeRetrofit();

    private Retrofit retrofit;

    public static PokeRetrofit getInstance() {
        return ourInstance;
    }

    private PokeRetrofit() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
