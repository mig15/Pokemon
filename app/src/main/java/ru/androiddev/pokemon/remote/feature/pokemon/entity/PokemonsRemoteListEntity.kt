package ru.androiddev.pokemon.remote.feature.pokemon.entity

import com.google.gson.annotations.SerializedName

data class PokemonsRemoteListEntity(
    val count: Int?,
    @SerializedName("next")
    val nextUrl: String?,
    @SerializedName("previous")
    val previousUrl: String?,
    val results: List<Pokemon>?
) {
    data class Pokemon(val name: String?, val url: String?)
}