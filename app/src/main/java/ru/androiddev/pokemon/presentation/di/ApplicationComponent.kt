package ru.androiddev.pokemon.presentation.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ru.androiddev.pokemon.presentation.MyApplication
import ru.androiddev.pokemon.presentation.di.module.FragmentBindingModule
import ru.androiddev.pokemon.presentation.di.module.NetworkModule
import ru.androiddev.pokemon.presentation.di.module.RepositoryModule

@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class,
    FragmentBindingModule::class,
    AndroidSupportInjectionModule::class,
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MyApplication)
}