package ru.androiddev.pokemon.remote.entity.pokemons

import com.google.gson.annotations.SerializedName

data class PokemonRemoteExpandedStatsEntity(
    val abilities: List<Ability>?,
    val moves: List<Move>?,
    val sprites: Sprite?,
    val name: String?,
    val height: Int?,
    @SerializedName("base_experience") val baseExperience: Int?,
    val weight: Int?
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