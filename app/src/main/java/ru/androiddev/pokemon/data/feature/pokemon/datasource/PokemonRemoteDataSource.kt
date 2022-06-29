package ru.androiddev.pokemon.data.feature.pokemon.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonExpandedStatsDataEntity
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonsListDataEntity
import ru.androiddev.pokemon.data.feature.pokemon.mapper.PokemonExpandedStatsMapper
import ru.androiddev.pokemon.data.feature.pokemon.mapper.PokemonMapper
import ru.androiddev.pokemon.remote.service.api.PokemonApi
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val remote: PokemonApi,
    private val mapper: PokemonMapper,
    private val pokemonExpandedStatsMapper: PokemonExpandedStatsMapper
) : PokemonDataSource {

    override suspend fun getPokemons(limit: Int, offset: Int): Flow<PokemonsListDataEntity> {
        return remote.getPokemons(limit, offset).map { mapper.mapFromRemote(it) }
    }

    override suspend fun getPokemonExpandedStats(url: String): Flow<PokemonExpandedStatsDataEntity> {
        return remote.getPokemonExpandedStats(url)
            .map { pokemonExpandedStatsMapper.mapFromRemote(it) }
    }
}