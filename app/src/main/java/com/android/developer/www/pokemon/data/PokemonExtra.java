package com.android.developer.www.pokemon.data;

import java.util.List;
import java.util.Map;

public class PokemonExtra {

    private String name;
    private Map<String, Integer> stats;
    private List<String> abilities;

    public void setName(String name) {
        this.name = name;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public List<String> getAbilities() {
        return abilities;
    }
}
