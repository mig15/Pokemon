package com.android.developer.www.pokemon.data;

import java.util.List;

public class PokemonRepository {

    private List<Pokemon> results;

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }

    public List<Pokemon> getResults() {
        return results;
    }
}
