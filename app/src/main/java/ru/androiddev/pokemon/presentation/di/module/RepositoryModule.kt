package ru.androiddev.pokemon.presentation.di.module

import dagger.Module
import dagger.Provides
import ru.androiddev.pokemon.data.feature.pokemon.repository.PokemonRepositoryImpl
import ru.androiddev.pokemon.data.feature.pokemon.datasource.PokemonCacheDataSource
import ru.androiddev.pokemon.data.feature.pokemon.datasource.PokemonRemoteDataSource
import ru.androiddev.pokemon.domain.feature.pokemon.repository.PokemonRepository

@Module
class RepositoryModule {

    @Provides
    fun providePokemonRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository {
        return pokemonRepositoryImpl
    }

    @Provides
    fun providePokemonRepositoryImpl(
        pokemonCacheDataSource: PokemonCacheDataSource,
        pokemonRemoteDataSource: PokemonRemoteDataSource
    ): PokemonRepositoryImpl {
        return PokemonRepositoryImpl(pokemonCacheDataSource, pokemonRemoteDataSource)
    }
}