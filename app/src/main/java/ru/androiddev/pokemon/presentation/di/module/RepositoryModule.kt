package ru.androiddev.pokemon.presentation.di.module

import dagger.Module
import dagger.Provides
import ru.androiddev.pokemon.data.feature.pokemon.repository.PokemonRepositoryImpl
import ru.androiddev.pokemon.data.feature.pokemon.mapper.PokemonExpandedStatsMapper
import ru.androiddev.pokemon.data.feature.pokemon.mapper.PokemonMapper
import ru.androiddev.pokemon.domain.feature.pokemon.repository.PokemonRepository
import ru.androiddev.pokemon.remote.service.api.PokemonApi

@Module
class RepositoryModule {

    @Provides
    fun providePokemonRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository {
        return pokemonRepositoryImpl
    }

    @Provides
    fun providePokemonRepositoryImpl(
        remote: PokemonApi,
        mapper: PokemonMapper,
        pokemonExpandedStatsMapper: PokemonExpandedStatsMapper
    ): PokemonRepositoryImpl {
        return PokemonRepositoryImpl(remote, mapper, pokemonExpandedStatsMapper)
    }
}