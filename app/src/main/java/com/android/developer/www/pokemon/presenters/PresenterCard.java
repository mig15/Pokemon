package com.android.developer.www.pokemon.presenters;

import android.util.Log;

import com.android.developer.www.pokemon.ActivityCard;
import com.android.developer.www.pokemon.data.PokemonExtra;
import com.android.developer.www.pokemon.models.ModelCard;

import java.io.IOException;
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
        view.showLoad();
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
                    view.showError();
                    return;
                }

                try {
                    String jsonResponse = response.body().string();
                    PokemonExtra pokemon = model.getPokemonStatsFromJson(jsonResponse);
                    setPokemonInfo(pokemon);
                } catch (IOException e) {
                    //TODO
                    Log.d("---My Log---", "catch");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("---My Log---", "onFailure");
                view.showError();
            }
        });
    }

    private void setPokemonInfo(PokemonExtra pokemon) {
        view.setName(pokemon.getName());
        view.setStats(getPokemonStats(pokemon));
        view.setAbilities(getPokemonAbilities(pokemon));
        view.showData();
    }

    private String getPokemonStats(PokemonExtra pokemon) {
        Map<String, Integer> stats = pokemon.getStats();

        String hitPoints = "hp = " + stats.get("hp");
        String speed = "speed = " + stats.get("speed");
        String attack = "special-attack = " + stats.get("special-attack");
        String defense = "special-defense = " + stats.get("special-defense");
        return hitPoints + "\n" + speed + "\n" + attack + "\n" + defense;
    }

    private String getPokemonAbilities(PokemonExtra pokemon) {
        List<String> abilitiesList = pokemon.getAbilities();
        StringBuilder abilities = new StringBuilder();

        for (String ability : abilitiesList) {
            abilities.append(ability).append("\n");
        }
        return abilities.toString();
    }
}
