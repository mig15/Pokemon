package ru.androiddev.pokemon.presentation.feature.pokemon.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.androiddev.pokemon.databinding.FragmentPokemonExpandedStatsBinding
import ru.androiddev.pokemon.presentation.base.BaseFragment
import ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel.PokemonViewModel
import ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel.PokemonViewModelFactory

class PokemonExpandedStatsFragment  : BaseFragment<PokemonViewModelFactory, PokemonViewModel>() {

    private lateinit var viewBinding: FragmentPokemonExpandedStatsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentPokemonExpandedStatsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun initViewModel() {
        setViewModel(PokemonViewModel::class.java)
    }
}