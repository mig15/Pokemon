package ru.androiddev.pokemon.presentation.feature.pokemon.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import ru.androiddev.pokemon.presentation.feature.pokemon.view.PokemonFragment

@Subcomponent
interface PokemonFragmentComponent : AndroidInjector<PokemonFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<PokemonFragment>
}