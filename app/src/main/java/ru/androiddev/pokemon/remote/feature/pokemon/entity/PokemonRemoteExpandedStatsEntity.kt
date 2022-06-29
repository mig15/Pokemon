package ru.androiddev.pokemon.remote.feature.pokemon.entity

import com.google.gson.annotations.SerializedName

data class PokemonRemoteExpandedStatsEntity(
    val abilities: List<Ability>?,
    val moves: List<Move>?,
    val sprites: Sprite?
) {

    data class Ability(
        val name: String?,
        val url: String?
    )

    data class Move(
        val name: String?,
        val url: String?
    )

    data class Sprite(val other: Other?) {

        data class Other(val home: Home?) {

            data class Home(@SerializedName("front_default") val frontDefault: String?)
        }
    }
}