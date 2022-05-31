package ru.androiddev.pokemon.presentation.feature.pokemon.di.activity

import dagger.Subcomponent
import dagger.android.AndroidInjector
import ru.androiddev.pokemon.presentation.MainActivity

@Subcomponent
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity>
}