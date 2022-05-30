package ru.androiddev.pokemon.presentation.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.androiddev.pokemon.presentation.feature.pokemon.di.PokemonFragmentModule
import ru.androiddev.pokemon.presentation.feature.pokemon.view.PokemonFragment

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = [(PokemonFragmentModule::class)])
    abstract fun bindPokemonFragment(): PokemonFragment
}