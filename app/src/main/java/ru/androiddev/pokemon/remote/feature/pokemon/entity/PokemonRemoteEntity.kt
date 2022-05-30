package ru.androiddev.pokemon.remote.feature.pokemon.entity

data class PokemonRemoteEntity(
    val abilities: List<Ability>?,
    val moves: List<Move>?
) {

    data class Ability(
        val name: String?,
        val url: String?
    )

    data class Move(
        val name: String,
        val url: String?
    )
}