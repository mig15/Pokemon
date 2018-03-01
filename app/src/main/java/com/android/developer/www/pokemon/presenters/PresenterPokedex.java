package com.android.developer.www.pokemon.presenters;

import com.android.developer.www.pokemon.data.Pokemon;
import com.android.developer.www.pokemon.fragments.main.Pokedex;
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
        model.cancelWork();
        view = null;
    }

    private void getData() {
        model.readPokemons(new ModelPokedex.ReadPokemonsCallback() {
            @Override
            public void onLoad(List<Pokemon> pokemons) {
                if (view == null) return;
                if (!pokemons.isEmpty()) {
                    view.showList(pokemons);
                } else {
                    view.showEmptyList();
                }
            }
        });
    }
}
