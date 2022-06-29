package ru.androiddev.pokemon.presentation.feature.pokemon.di.stats

import dagger.Subcomponent
import dagger.android.AndroidInjector
import ru.androiddev.pokemon.presentation.feature.pokemon.view.PokemonExpandedStatsFragment

@Subcomponent
interface PokemonExpandedStatsFragmentComponent : AndroidInjector<PokemonExpandedStatsFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<PokemonExpandedStatsFragment>
}