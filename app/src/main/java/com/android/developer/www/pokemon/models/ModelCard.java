package com.android.developer.www.pokemon.models;

import com.android.developer.www.pokemon.data.PokemonExtra;
import com.android.developer.www.pokemon.retrofit.PokeRetrofit;
import com.android.developer.www.pokemon.retrofit.PokemonApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class ModelCard {

    public Call<ResponseBody> getRequest(int id) {
        PokemonApi service = PokeRetrofit.getInstance().getRetrofit().create(PokemonApi.class);
        return service.getStringResponse(id);
    }

    //Гигантский json, легче достать нужное руками
    public PokemonExtra getPokemonStatsFromJson(String jsonResponse) {
        PokemonExtra pokemonExtra = new PokemonExtra();
        Map<String, Integer> statsList = new HashMap<>();
        List<String> abilitiesList = new ArrayList<>();

        try {
            JSONObject fullObject = new JSONObject(jsonResponse);

            pokemonExtra.setName(fullObject.getString("name"));

            JSONArray abilities = fullObject.getJSONArray("abilities");
            for (int i = 0; i < abilities.length(); i++) {
                JSONObject innerObject = abilities.getJSONObject(i);
                String abilityName = innerObject.getJSONObject("ability").getString("name");
                abilitiesList.add(abilityName);
            }
            pokemonExtra.setAbilities(abilitiesList);

            JSONArray stats = fullObject.getJSONArray("stats");
            for (int i = 0; i < stats.length(); i++) {
                JSONObject innerObject = stats.getJSONObject(i);
                String name = innerObject.getJSONObject("stat").getString("name");
                int value = innerObject.getInt("base_stat");
                statsList.put(name, value);
            }
            pokemonExtra.setStats(statsList);
        } catch (JSONException e) {
            //TODO
        }
        return pokemonExtra;
    }
}
