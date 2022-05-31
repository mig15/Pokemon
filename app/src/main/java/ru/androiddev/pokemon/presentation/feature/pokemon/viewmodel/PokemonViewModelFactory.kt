package ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.androiddev.pokemon.domain.feature.pokemon.interactor.PokemonRemoteExpandedStatsInteractor
import ru.androiddev.pokemon.domain.feature.pokemon.interactor.PokemonRemoteInteractor

class PokemonViewModelFactory(
    private val pokemonUseCase: PokemonRemoteInteractor,
    private val pokemonExpandedStatsUseCase: PokemonRemoteExpandedStatsInteractor

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            return PokemonViewModel(
                pokemonUseCase = pokemonUseCase,
                pokemonExpandedStatsUseCase = pokemonExpandedStatsUseCase

            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}