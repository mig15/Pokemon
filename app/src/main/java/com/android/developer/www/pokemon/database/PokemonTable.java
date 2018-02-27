package com.android.developer.www.pokemon.database;

public class PokemonTable {

    public static final String TABLE = "pokemons";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";

    public static final String CREATE_SCRIPT =
            String.format("create table %s ("
                            + "%s integer primary key autoincrement,"
                            + "%s text" + ");",
                    TABLE, COLUMN_ID, COLUMN_NAME);
}
