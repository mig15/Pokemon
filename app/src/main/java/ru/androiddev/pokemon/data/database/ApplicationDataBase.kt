package ru.androiddev.pokemon.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.androiddev.pokemon.data.database.entity.pokemons.PokemonDataBaseEntity

@Database(version = 1, entities = [PokemonDataBaseEntity::class])
abstract class ApplicationDataBase : RoomDatabase() {
}