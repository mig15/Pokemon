package ru.androiddev.pokemon.data.feature.pokemon.entity

class PokemonsListDataEntity(
    val count: Int?,
    val nextUrl: String?,
    val previousUrl: String?,
    val results: List<Pokemon>?
) {
    data class Pokemon(val name: String?, val url: String?)
}