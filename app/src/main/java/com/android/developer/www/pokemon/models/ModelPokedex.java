package com.android.developer.www.pokemon.models;

import android.database.Cursor;
import android.os.AsyncTask;

import com.android.developer.www.pokemon.data.Pokemon;
import com.android.developer.www.pokemon.database.DBHelper;
import com.android.developer.www.pokemon.database.PokemonTable;

import java.util.ArrayList;
import java.util.List;

public class ModelPokedex {

    private DBHelper dbHelper;
    private ReadPokemonsTask readTask;

    public ModelPokedex(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void readPokemons(ReadPokemonsCallback callback) {
        readTask = new ReadPokemonsTask(callback);
        readTask.execute();
    }

    public void cancelWork(){
        if (readTask != null && !readTask.isCancelled()) readTask.cancel(true);
    }

    public interface ReadPokemonsCallback {
        void onLoad(List<Pokemon> pokemons);
    }

    private class ReadPokemonsTask extends AsyncTask<Void, Void, List<Pokemon>> {

        private final ReadPokemonsCallback callback;

        ReadPokemonsTask(ReadPokemonsCallback callback) {
            this.callback = callback;
        }

        @Override
        protected List<Pokemon> doInBackground(Void... params) {
            List<Pokemon> pokemons = new ArrayList<>();
            Cursor cursor = dbHelper.getReadableDatabase().query(PokemonTable.TABLE, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    Pokemon pokemon = new Pokemon();
                    pokemon.setName(cursor.getString(cursor.getColumnIndex(PokemonTable.COLUMN_NAME)));
                    pokemon.setUrl(cursor.getString(cursor.getColumnIndex(PokemonTable.COLUMN_URL)));
                    pokemons.add(pokemon);
                } while (cursor.moveToNext());
            }
            cursor.close();
            dbHelper.close();
            return pokemons;
        }

        @Override
        protected void onPostExecute(List<Pokemon> pokemons) {
            if (callback != null) {
                callback.onLoad(pokemons);
            }
        }
    }
}
