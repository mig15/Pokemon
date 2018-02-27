package com.android.developer.www.pokemon.presenters;

import android.util.Log;

import com.android.developer.www.pokemon.ActivityCard;
import com.android.developer.www.pokemon.data.PokemonExtra;
import com.android.developer.www.pokemon.models.ModelCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterCard {

    private ActivityCard view;
    private ModelCard model;

    public PresenterCard(ModelCard model) {
        this.model = model;
    }

    public void attachView(ActivityCard view) {
        this.view = view;
    }

    public void viewReady() {
        sendRequest();
    }

    public void detachView() {
        view = null;
    }

    public void sendRequest() {
        Call<ResponseBody> res = model.getRequest(view.getPokemonId());
        res.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    //TODO
                    //throw new IOException("Unexpected code " + response);
                }

                try {
                    String s = response.body().string();
                    PokemonExtra pokemon = getPokemonStatsFromJson(s);
                    setInfo(pokemon);
                } catch (IOException e) {
                    //TODO
                    Log.d("---My Log---", "catch");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("---My Log---", "onFailure");
            }
        });
    }

    private PokemonExtra getPokemonStatsFromJson(String jsonResponse) {
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

    private void setInfo(PokemonExtra pokemon) {
        view.setName(pokemon.getName());
    }

    private void ff(String veryLongString) {
        int maxLogSize = 1000;
        for(int i = 0; i <= veryLongString.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i+1) * maxLogSize;
            end = end > veryLongString.length() ? veryLongString.length() : end;
            Log.d("---My Log---", veryLongString.substring(start, end));
        }
    }

    private void showPokemon(PokemonExtra pokemonExtra) {
        String name = pokemonExtra.getName();
        Log.d("---My Log---", "name " + name);
        Map<String, Integer> stats = pokemonExtra.getStats();

        for (Map.Entry<String, Integer> pair : stats.entrySet()) {
            String key = pair.getKey();
            Integer value = pair.getValue();
            Log.d("---My Log---", "stat: " + key + " value " + value);
        }

        for (String ability : pokemonExtra.getAbilities()) {
            Log.d("---My Log---", "ability" + ability);
        }
    }
}
