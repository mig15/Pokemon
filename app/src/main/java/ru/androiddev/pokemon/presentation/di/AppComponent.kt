package ru.androiddev.pokemon.presentation.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ru.androiddev.pokemon.presentation.MainApp
import ru.androiddev.pokemon.presentation.di.modules.ActivityBindingModule
import ru.androiddev.pokemon.presentation.di.modules.FragmentBindingModule
import ru.androiddev.pokemon.presentation.di.modules.NetworkModule
import ru.androiddev.pokemon.presentation.di.modules.RepositoryModule

@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class,
    FragmentBindingModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: MainApp)
}