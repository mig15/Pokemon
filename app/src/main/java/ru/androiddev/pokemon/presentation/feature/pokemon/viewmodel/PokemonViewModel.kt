package ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonsListDataEntity
import ru.androiddev.pokemon.domain.feature.pokemon.interactor.PokemonRemoteInteractor
import ru.androiddev.pokemon.presentation.base.BaseViewModel
import ru.androiddev.pokemon.presentation.feature.pokemon.list.PokemonItem

class PokemonViewModel(
    private val pokemonUseCase: PokemonRemoteInteractor
) : BaseViewModel() {

    private var pokemonsRequestOffset = 0

    val itemsLiveData = MutableLiveData<List<PokemonItem>>()

    fun getPokemons() {
        if (!pokemonUseCase.isActive() && pokemonsRequestOffset < FIRST_GENERATION) {
            pokemonUseCase.execute(
                params = PokemonRemoteInteractor.Params(
                    limit = POKEMONS_REQUEST_LIMIT,
                    offset = pokemonsRequestOffset
                ),
                onComplete = {
                    pokemonsRequestOffset += 50
                    itemsLiveData.value = mapItems(it)
                },
                onError = {
                    Log.d("---My Log---", "error: $it")
                }
            )
        }
    }

    fun unsubscribe() {
        pokemonUseCase.unsubscribe()
    }

    private fun mapItems(data: PokemonsListDataEntity): List<PokemonItem> {
        return data.results?.map { item ->
            PokemonItem(name = item.name ?: "", url = item.url ?: "")
                .apply {
                    onClick = {

                    }
                }
        } ?: emptyList()
    }

    companion object {
        const val FIRST_GENERATION = 150
        const val POKEMONS_REQUEST_LIMIT = 50
    }
}