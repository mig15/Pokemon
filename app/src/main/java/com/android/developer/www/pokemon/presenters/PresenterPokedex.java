package com.android.developer.www.pokemon.presenters;

import android.util.Log;

import com.android.developer.www.pokemon.fragments.main.Pokedex;
import com.android.developer.www.pokemon.data.Pokemon;
import com.android.developer.www.pokemon.models.ModelPokedex;

import java.util.List;

public class PresenterPokedex {

    private Pokedex view;
    private ModelPokedex model;

    public PresenterPokedex(ModelPokedex model) {
        this.model = model;
    }

    public void attachView(Pokedex view) {
        this.view = view;
    }

    public void viewReady() {
        view.showLoad();
        getData();
    }

    public void detachView() {
        view = null;
    }

    private void getData() {
        model.readPokemons(new ModelPokedex.ReadPokemonsCallback() {
            @Override
            public void onLoad(List<Pokemon> pokemons) {
                if (!pokemons.isEmpty()) {
                    view.showList(pokemons);
                } else {
                    view.showEmptyList();
                }
            }
        });
    }

    private void addPokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Pikachu");
        model.addPokemon(pokemon, new ModelPokedex.CompleteCallback() {
            @Override
            public void onComplete() {
                Log.d("---My Log---", "add complete");
            }
        });
    }
}
