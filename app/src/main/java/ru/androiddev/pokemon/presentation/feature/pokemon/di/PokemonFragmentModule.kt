package ru.androiddev.pokemon.presentation.feature.pokemon.di

import dagger.Module
import dagger.Provides
import ru.androiddev.pokemon.domain.feature.pokemon.interactor.PokemonRemoteExpandedStatsInteractor
import ru.androiddev.pokemon.domain.feature.pokemon.interactor.PokemonRemoteInteractor
import ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel.PokemonViewModelFactory

@Module
class PokemonFragmentModule {

    @Provides
    fun providePokemonViewModelFactory(
        pokemonUseCase: PokemonRemoteInteractor,
        pokemonExpandedStatsUseCase: PokemonRemoteExpandedStatsInteractor
    ): PokemonViewModelFactory {
        return PokemonViewModelFactory(
            pokemonUseCase = pokemonUseCase,
            pokemonExpandedStatsUseCase = pokemonExpandedStatsUseCase
        )
    }
}