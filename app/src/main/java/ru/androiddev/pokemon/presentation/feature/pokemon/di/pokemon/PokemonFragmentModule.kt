package ru.androiddev.pokemon.presentation.feature.pokemon.di.pokemon

import dagger.Module
import dagger.Provides
import ru.androiddev.pokemon.domain.feature.pokemon.interactor.PokemonRemoteInteractor
import ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel.PokemonViewModelFactory

@Module
class PokemonFragmentModule {

    @Provides
    fun providePokemonViewModelFactory(
        pokemonUseCase: PokemonRemoteInteractor
    ): PokemonViewModelFactory {
        return PokemonViewModelFactory(
            pokemonUseCase = pokemonUseCase
        )
    }
}