package ru.androiddev.pokemon.data.feature.pokemon.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonsListDataEntity
import ru.androiddev.pokemon.data.feature.pokemon.mapper.PokemonMapper
import ru.androiddev.pokemon.remote.service.api.PokemonApi
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    val remote: PokemonApi,
    val mapper: PokemonMapper
) : PokemonDataSource {

    override suspend fun getPokemons(limit: Int, offset: Int): Flow<PokemonsListDataEntity> {
        return remote.getPokemons(limit, offset).map { mapper.mapFromRemote(it) }
    }
}