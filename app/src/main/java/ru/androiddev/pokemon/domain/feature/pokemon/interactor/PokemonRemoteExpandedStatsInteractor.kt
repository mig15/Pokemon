package ru.androiddev.pokemon.domain.feature.pokemon.interactor

import kotlinx.coroutines.flow.first
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonExpandedStatsDataEntity
import ru.androiddev.pokemon.domain.base.BaseInteractor
import ru.androiddev.pokemon.domain.feature.pokemon.repository.PokemonRepository
import javax.inject.Inject

class PokemonRemoteExpandedStatsInteractor @Inject constructor(private val repository: PokemonRepository)
    : BaseInteractor<PokemonExpandedStatsDataEntity, PokemonRemoteExpandedStatsInteractor.Params>() {

    override suspend fun executeOnBackground(params: Params): PokemonExpandedStatsDataEntity {
        return repository.getPokemonExpandedStats(params.url).first()
    }

    data class Params(val url: String)
}