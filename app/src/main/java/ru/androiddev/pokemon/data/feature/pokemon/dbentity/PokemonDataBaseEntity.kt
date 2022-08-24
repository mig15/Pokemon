package ru.androiddev.pokemon.data.feature.pokemon.dbentity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class PokemonDataBaseEntity(
    val name: String,
    val height: Int,
    @ColumnInfo(name = "base_experience") val baseExperience: Int,
    val weight: Int
)