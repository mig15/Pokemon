package com.android.developer.www.pokemon.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;

import com.android.developer.www.pokemon.data.Pokemon;
import com.android.developer.www.pokemon.data.PokemonExtra;
import com.android.developer.www.pokemon.database.DBHelper;
import com.android.developer.www.pokemon.database.PokemonTable;
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

    private DBHelper dbHelper;

    public ModelCard(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

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
            throw new RuntimeException("Error");
        }
        return pokemonExtra;
    }

    public void addPokemon(Pokemon pokemon, CompleteCallback callback) {
        AddPokemonTask task = new AddPokemonTask(callback);
        task.execute(pokemon);
    }

    public void removePokemon(String name, CompleteCallback completeCallback) {
        RemovePokemonTask task = new RemovePokemonTask(completeCallback);
        task.execute(name);
    }

    public void checkPokemon(String name, CompleteCheck completeCallback) {
        CheckPokemonTask task = new CheckPokemonTask(completeCallback);
        task.execute(name);
    }

    public interface CompleteCallback {
        void onComplete();
    }

    public interface CompleteCheck {
        void onComplete(boolean result);
    }

    private class AddPokemonTask extends AsyncTask<Pokemon, Void, Void> {

        private final CompleteCallback callback;

        AddPokemonTask(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Pokemon... params) {
            ContentValues cv = new ContentValues();
            cv.put(PokemonTable.COLUMN_NAME, params[0].getName());
            cv.put(PokemonTable.COLUMN_URL, params[0].getUrl());
            dbHelper.getWritableDatabase().insert(PokemonTable.TABLE, null, cv);
            dbHelper.close();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    private class RemovePokemonTask extends AsyncTask<String, Void, Void> {

        private final CompleteCallback callback;

        RemovePokemonTask(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(String... params) {
            dbHelper.getWritableDatabase()
                    .delete(PokemonTable.TABLE, "name = ?" + params[0], null);
            dbHelper.close();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    private class CheckPokemonTask extends AsyncTask<String, Void, Boolean> {

        private final CompleteCheck callback;

        CheckPokemonTask(CompleteCheck callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            boolean found;
            String sql = "SELECT * FROM "
                    + PokemonTable.TABLE
                    + " WHERE "
                    + PokemonTable.COLUMN_NAME
                    + " = ?";
            Cursor c = dbHelper.getWritableDatabase().rawQuery(sql, new String[] {params[0]});
            found = c.moveToFirst();
            c.close();
            dbHelper.close();
            return found;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (callback != null) {
                callback.onComplete(result);
            }
        }
    }
}
