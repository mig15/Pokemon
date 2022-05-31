package ru.androiddev.pokemon.presentation

import android.os.Bundle
import ru.androiddev.pokemon.databinding.ActivityMainBinding
import ru.androiddev.pokemon.presentation.base.BaseActivity
import ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel.PokemonViewModel
import ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel.PokemonViewModelFactory
import ru.androiddev.pokemon.presentation.extension.addFragment
import ru.androiddev.pokemon.presentation.feature.pokemon.view.PokemonFragment

class MainActivity : BaseActivity<PokemonViewModelFactory, PokemonViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        addFragment(PokemonFragment(), viewBinding.activityMainContainer.id)
    }

    override fun initViewModel() {}
}