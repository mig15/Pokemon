package ru.androiddev.pokemon.presentation.feature.pokemon.di.stats

import dagger.Module
import dagger.Provides
import ru.androiddev.pokemon.domain.feature.pokemon.interactor.PokemonRemoteExpandedStatsInteractor
import ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel.PokemonExpandedStatsViewModelFactory

@Module
class PokemonExpandedStatsFragmentModule() {

    @Provides
    fun providePokemonExpandedStatsViewModelFactory(
        pokemonExpandedStatsUseCase: PokemonRemoteExpandedStatsInteractor
    ): PokemonExpandedStatsViewModelFactory {
        return PokemonExpandedStatsViewModelFactory(
            pokemonExpandedStatsUseCase = pokemonExpandedStatsUseCase
        )
    }
}