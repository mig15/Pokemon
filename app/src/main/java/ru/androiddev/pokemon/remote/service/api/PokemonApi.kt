package ru.androiddev.pokemon.remote.service.api

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androiddev.pokemon.remote.feature.pokemon.entity.PokemonRemoteEntity
import ru.androiddev.pokemon.remote.feature.pokemon.entity.PokemonsListRemoteEntity

interface PokemonApi {

    @GET("pokemon")
    fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Flow<PokemonsListRemoteEntity>

    @GET("pokemon/{number}")
    fun getPokemon(@Path ("number") number: Int): Flow<PokemonRemoteEntity>
}