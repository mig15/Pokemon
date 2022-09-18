package ru.androiddev.pokemon.presentation.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.androiddev.pokemon.presentation.MainActivity
import ru.androiddev.pokemon.presentation.feature.pokemon.di.activity.MainActivityModule

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity
}