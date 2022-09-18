package ru.androiddev.pokemon.data.database.entity.pokemons

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonDataBaseEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val height: Int,
    @ColumnInfo(name = "base_experience") val baseExperience: Int,
    val weight: Int
)