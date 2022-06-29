package ru.androiddev.pokemon.data.feature.pokemon.mapper

import ru.androiddev.pokemon.data.RemoteMapper
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonExpandedStatsDataEntity
import ru.androiddev.pokemon.remote.feature.pokemon.entity.PokemonRemoteExpandedStatsEntity
import javax.inject.Inject

class PokemonExpandedStatsMapper @Inject constructor() :
    RemoteMapper<PokemonRemoteExpandedStatsEntity, PokemonExpandedStatsDataEntity> {

    override fun mapFromRemote(remote: PokemonRemoteExpandedStatsEntity): PokemonExpandedStatsDataEntity {
        return PokemonExpandedStatsDataEntity(
            abilities = remote.abilities?.map { item ->
                PokemonExpandedStatsDataEntity.Ability(
                    name = item.name,
                    url = item.url
                )
            },
            moves = remote.moves?.map { item ->
                PokemonExpandedStatsDataEntity.Move(
                    name = item.name,
                    url = item.url
                )
            },
            sprites = PokemonExpandedStatsDataEntity.Sprite(
                PokemonExpandedStatsDataEntity.Sprite.Other(
                    PokemonExpandedStatsDataEntity.Sprite.Other.Home(
                        remote.sprites?.other?.home?.frontDefault
                    )
                )
            )
        )
    }

    override fun mapToRemote(entity: PokemonExpandedStatsDataEntity): PokemonRemoteExpandedStatsEntity {
        TODO("Not yet implemented")
    }
}