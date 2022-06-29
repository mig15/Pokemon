package ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.androiddev.pokemon.domain.feature.pokemon.interactor.PokemonRemoteExpandedStatsInteractor

class PokemonExpandedStatsViewModelFactory(
    private val pokemonExpandedStatsUseCase: PokemonRemoteExpandedStatsInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonExpandedStatsViewModel::class.java)) {
            return PokemonExpandedStatsViewModel(
                pokemonExpandedStatsUseCase = pokemonExpandedStatsUseCase
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}