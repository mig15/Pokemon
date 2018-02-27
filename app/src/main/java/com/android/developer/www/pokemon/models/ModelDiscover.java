package com.android.developer.www.pokemon.models;

import com.android.developer.www.pokemon.data.PokemonRepository;
import com.android.developer.www.pokemon.retrofit.PokeRetrofit;
import com.android.developer.www.pokemon.retrofit.PokemonApi;

import retrofit2.Call;

public class ModelDiscover {

    public Call<PokemonRepository> getRequest(int offset) {
        PokemonApi service = PokeRetrofit.getInstance().getRetrofit().create(PokemonApi.class);
        return service.getPokemonsList(20, offset);
    }
}
