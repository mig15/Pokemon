package ru.androiddev.pokemon.data.feature.pokemon.repository

import kotlinx.coroutines.flow.Flow
import ru.androiddev.pokemon.data.feature.pokemon.datasource.PokemonCacheDataSource
import ru.androiddev.pokemon.data.feature.pokemon.datasource.PokemonRemoteDataSource
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonsListDataEntity
import ru.androiddev.pokemon.domain.feature.pokemon.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    val pokemonCacheDataSource: PokemonCacheDataSource,
    val pokemonRemoteDataSource: PokemonRemoteDataSource
    ) : PokemonRepository {

    override suspend fun getPokemons(limit: Int, offset: Int): Flow<PokemonsListDataEntity> {
        return pokemonRemoteDataSource.getPokemons(limit, offset)
    }
}