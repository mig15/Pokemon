package ru.androiddev.pokemon.domain.feature.pokemon.repository

import kotlinx.coroutines.flow.Flow
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonExpandedStatsDataEntity
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonsListDataEntity

interface PokemonRepository {

    suspend fun getPokemons(limit: Int, offset: Int): Flow<PokemonsListDataEntity>

    suspend fun getPokemonExpandedStats(pokemonNumber: Int): Flow<PokemonExpandedStatsDataEntity>

}