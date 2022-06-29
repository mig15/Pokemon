package ru.androiddev.pokemon.data.feature.pokemon.mapper

import ru.androiddev.pokemon.data.RemoteMapper
import ru.androiddev.pokemon.data.feature.pokemon.entity.PokemonsListDataEntity
import ru.androiddev.pokemon.remote.feature.pokemon.entity.PokemonsRemoteListEntity
import javax.inject.Inject

class PokemonMapper @Inject constructor(): RemoteMapper<PokemonsRemoteListEntity, PokemonsListDataEntity> {

    override fun mapFromRemote(remote: PokemonsRemoteListEntity): PokemonsListDataEntity {
        return with(remote) {
            PokemonsListDataEntity(
                count = count,
                nextUrl = nextUrl,
                previousUrl = previousUrl,
                results = results?.map {
                    PokemonsListDataEntity.Pokemon(
                        name = it.name,
                        url = it.url
                    )
                }
            )
        }
    }

    override fun mapToRemote(entity: PokemonsListDataEntity): PokemonsRemoteListEntity {
        TODO("Not yet implemented")
    }
}