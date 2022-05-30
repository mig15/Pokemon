package ru.androiddev.pokemon.domain.feature.pokemon.interactor

import kotlinx.coroutines.flow.first
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonsListDataEntity
import ru.androiddev.pokemon.domain.base.BaseInteractor
import ru.androiddev.pokemon.domain.feature.pokemon.repository.PokemonRepository
import javax.inject.Inject

class PokemonRemoteInteractor @Inject constructor(
    private val repository: PokemonRepository
) : BaseInteractor<PokemonsListDataEntity, PokemonRemoteInteractor.Params>() {

    override suspend fun executeOnBackground(params: Params): PokemonsListDataEntity {
        return repository.getPokemons(params.limit, params.offset).first()
    }

    data class Params(val limit: Int, val offset: Int)
}
