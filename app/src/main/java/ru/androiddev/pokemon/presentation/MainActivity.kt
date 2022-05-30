package ru.androiddev.pokemon.presentation

import android.os.Bundle
import ru.androiddev.pokemon.R
import ru.androiddev.pokemon.presentation.base.BaseActivity
import ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel.PokemonViewModel
import ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel.PokemonViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import ru.androiddev.pokemon.presentation.extension.addFragment
import ru.androiddev.pokemon.presentation.feature.pokemon.view.PokemonFragment

class MainActivity : BaseActivity<PokemonViewModelFactory, PokemonViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(PokemonFragment(), R.id.activityMainContainer)
    }

    override fun initViewModel() {}
}