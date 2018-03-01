package com.android.developer.www.pokemon.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NAME = "pokemon";
    private static final int VERSION = 1;

    private static DBHelper ourInstance = null;

    public static DBHelper getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DBHelper(context);
        }
        return ourInstance;
    }

    private DBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PokemonTable.CREATE_SCRIPT);
        Log.d("---My Log---", "onCreate DB");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
