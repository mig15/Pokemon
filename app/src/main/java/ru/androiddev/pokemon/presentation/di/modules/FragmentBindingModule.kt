package ru.androiddev.pokemon.presentation.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.androiddev.pokemon.presentation.feature.pokemon.di.pokemon.PokemonFragmentModule
import ru.androiddev.pokemon.presentation.feature.pokemon.di.stats.PokemonExpandedStatsFragmentModule
import ru.androiddev.pokemon.presentation.feature.pokemon.view.PokemonExpandedStatsFragment
import ru.androiddev.pokemon.presentation.feature.pokemon.view.PokemonFragment

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = [(PokemonFragmentModule::class)])
    abstract fun bindPokemonFragment(): PokemonFragment

    @ContributesAndroidInjector(modules = [(PokemonExpandedStatsFragmentModule::class)])
    abstract fun bindPokemonExpandedStatsFragment(): PokemonExpandedStatsFragment
}