package ru.androiddev.pokemon.data.db

import androidx.room.Database
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonExpandedStatsDataEntity

@Database(version = 1, entities = [PokemonExpandedStatsDataEntity::class])
abstract class ApplicationDataBase {
}