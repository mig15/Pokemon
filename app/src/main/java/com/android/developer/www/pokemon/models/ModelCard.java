package com.android.developer.www.pokemon.models;

import com.android.developer.www.pokemon.retrofit.PokeRetrofit;
import com.android.developer.www.pokemon.retrofit.PokemonApi;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class ModelCard {

    public Call<ResponseBody> getRequest(int id) {
        PokemonApi service = PokeRetrofit.getInstance().getRetrofit().create(PokemonApi.class);
        return service.getStringResponse(id);
    }
}
