package com.android.developer.www.pokemon.presenters;

import android.util.Log;

import com.android.developer.www.pokemon.ActivityCard;
import com.android.developer.www.pokemon.data.Pokemon;
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

    private static final String POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/";

    private ActivityCard view;
    private ModelCard model;

    private boolean havePokemon;

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

    private void sendRequest() {
        Call<ResponseBody> res = model.getRequest(view.getPokemonId());
        res.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    Log.d("---My Log---", "not successful");
                    view.showError();
                    return;
                }

                try {
                    String jsonResponse = response.body().string();
                    PokemonExtra pokemon = model.getPokemonStatsFromJson(jsonResponse);
                    verifyPokemon(pokemon);
                    setPokemonInfo(pokemon);
                } catch (IOException e) {
                    throw new RuntimeException("Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("---My Log---", "onFailure");
                view.showError();
            }
        });
    }

    private void verifyPokemon(PokemonExtra pokemon) {
        model.checkPokemon(pokemon.getName(), new ModelCard.CompleteCheck() {
            @Override
            public void onComplete(boolean result) {
                havePokemon = result;
                if (result) {
                    view.setDeleteIcon();
                } else {
                    view.setSaveIcon();
                }
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

        StringBuilder sb = new StringBuilder();
        sb.append("hp = ").append(stats.get("hp")).append("\n");
        sb.append("speed = ").append(stats.get("speed")).append("\n");
        sb.append("special-attack = ").append(stats.get("special-attack")).append("\n");
        sb.append("special-defense = ").append(stats.get("special-defense"));
        return sb.toString();
    }

    private String getPokemonAbilities(PokemonExtra pokemon) {
        List<String> abilitiesList = pokemon.getAbilities();
        StringBuilder abilities = new StringBuilder();

        for (String ability : abilitiesList) {
            abilities.append(ability).append("\n");
        }
        return abilities.toString();
    }

    public void onDataBase() {
        if (havePokemon) {
            deletePokemon();
        } else {
            writePokemon();
        }
    }

    private void writePokemon() {
        String name = view.getPokemonName();
        String url = POKEMON_URL + view.getPokemonId();
        Pokemon pokemon = new Pokemon();
        pokemon.setName(name);
        pokemon.setUrl(url);
        model.addPokemon(pokemon, new ModelCard.CompleteCallback() {
            @Override
            public void onComplete() {
                view.showToast("Покемон добавлен в покедекс");
                view.setDeleteIcon();
                havePokemon = true;
            }
        });
    }

    private void deletePokemon() {
        String name = view.getPokemonName();
        model.removePokemon(name, new ModelCard.CompleteCallback() {
            @Override
            public void onComplete() {
                view.showToast("Покемон удален из покедекса");
                view.setSaveIcon();
                havePokemon = false;
            }
        });
    }
}
