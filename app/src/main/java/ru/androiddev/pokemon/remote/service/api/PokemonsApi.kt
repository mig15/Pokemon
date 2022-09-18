package ru.androiddev.pokemon.remote.service.api

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import ru.androiddev.pokemon.remote.entity.pokemons.PokemonRemoteExpandedStatsEntity
import ru.androiddev.pokemon.remote.entity.pokemons.PokemonsRemoteListEntity

interface PokemonsApi {

    @GET("pokemon")
    fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Flow<PokemonsRemoteListEntity>

    @GET
    fun getPokemonExpandedStats(@Url url: String): Flow<PokemonRemoteExpandedStatsEntity>
}