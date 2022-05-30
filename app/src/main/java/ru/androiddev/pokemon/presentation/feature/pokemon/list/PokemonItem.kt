package ru.androiddev.pokemon.presentation.feature.pokemon.list

data class PokemonItem(val name: String, val url: String) {

    var onClick: ((url: String) -> Unit)? = null
}