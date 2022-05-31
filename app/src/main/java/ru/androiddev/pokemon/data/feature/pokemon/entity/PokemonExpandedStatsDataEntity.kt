package ru.androiddev.pokemon.data.feature.pokemon.entity

data class PokemonExpandedStatsDataEntity(
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