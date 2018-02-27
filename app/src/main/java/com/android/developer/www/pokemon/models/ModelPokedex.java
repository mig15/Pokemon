package com.android.developer.www.pokemon.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;

import com.android.developer.www.pokemon.data.Pokemon;
import com.android.developer.www.pokemon.database.DBHelper;
import com.android.developer.www.pokemon.database.PokemonTable;

import java.util.ArrayList;
import java.util.List;

public class ModelPokedex {

    private DBHelper dbHelper;

    public ModelPokedex(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void readPokemons(ReadPokemonsCallback callback) {
        ReadPokemonsTask task = new ReadPokemonsTask(callback);
        task.execute();
    }

    public interface ReadPokemonsCallback {
        void onLoad(List<Pokemon> pokemons);
    }

    public void addPokemon(Pokemon pokemon, CompleteCallback callback) {
        AddPokemonTask task = new AddPokemonTask(callback);
        task.execute(pokemon);
    }

    public void removePokemon(CompleteCallback completeCallback) {
        RemovePokemonTask task = new RemovePokemonTask(completeCallback);
        task.execute();
    }

    public interface CompleteCallback {
        void onComplete();
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
            while (cursor.moveToNext()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setName(cursor.getString(cursor.getColumnIndex(PokemonTable.COLUMN_NAME)));
                pokemons.add(pokemon);
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

    private class AddPokemonTask extends AsyncTask<Pokemon, Void, Void> {

        private final CompleteCallback callback;

        AddPokemonTask(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Pokemon... params) {
            ContentValues cv = new ContentValues();
            cv.put(PokemonTable.COLUMN_NAME, params[0].getName());
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

    private class RemovePokemonTask extends AsyncTask<Void, Void, Void> {

        private final CompleteCallback callback;

        RemovePokemonTask(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            //TODO
            dbHelper.getWritableDatabase().delete(PokemonTable.TABLE, null, null);
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
}
