package ru.androiddev.pokemon.data.feature.pokemon.datasource

import kotlinx.coroutines.flow.Flow
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonExpandedStatsDataEntity
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonsListDataEntity
import javax.inject.Inject

class PokemonCacheDataSource @Inject constructor(): PokemonDataSource {

    override suspend fun getPokemons(limit: Int, offset: Int): Flow<PokemonsListDataEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonExpandedStats(pokemonNumber: Int): Flow<PokemonExpandedStatsDataEntity> {
        TODO("Not yet implemented")
    }
}