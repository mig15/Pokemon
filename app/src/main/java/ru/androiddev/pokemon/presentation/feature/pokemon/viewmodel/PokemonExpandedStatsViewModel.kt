package ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonExpandedStatsDataEntity
import ru.androiddev.pokemon.domain.feature.pokemon.interactor.PokemonRemoteExpandedStatsInteractor
import ru.androiddev.pokemon.presentation.base.BaseViewModel

class PokemonExpandedStatsViewModel(
    private val pokemonExpandedStatsUseCase: PokemonRemoteExpandedStatsInteractor
    ) : BaseViewModel() {

    private var pokemonExpandedStatsJob: Job? = null

    val itemsLiveData = MutableLiveData<PokemonExpandedStatsDataEntity>()

    fun getPokemonExpandedStats(url: String) {
        pokemonExpandedStatsJob = viewModelScope.launch(superJob) {
            pokemonExpandedStatsUseCase.execute(
                params = PokemonRemoteExpandedStatsInteractor.Params(url),
                onComplete = {
                    itemsLiveData.value = it
                },
                onError = {
                    Log.d("---My Log---", "error 2: $it")
                }
            )
        }
    }
}