package ru.androiddev.pokemon.presentation.feature.pokemon.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.androiddev.pokemon.databinding.FragmentPokemonExpandedStatsBinding
import ru.androiddev.pokemon.presentation.base.BaseFragment
import ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel.PokemonExpandedStatsViewModel
import ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel.PokemonExpandedStatsViewModelFactory

class PokemonExpandedStatsFragment
    : BaseFragment<PokemonExpandedStatsViewModelFactory, PokemonExpandedStatsViewModel>() {

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
        val url = arguments?.getString(POKEMON_ID_BUNDLE_KEY)

        initLiveData()

        viewModel.getPokemonExpandedStats(url ?: "") //TODO null url
    }

    override fun initViewModel() {
        setViewModel(PokemonExpandedStatsViewModel::class.java)
    }

    private fun initLiveData() {
        viewModel.itemsLiveData.observe(this) {
            Glide
                .with(this)
                .load(it.sprites?.other?.home?.frontDefault)
                .circleCrop()
                .into(viewBinding.fragmentPokemonExpandedStatsPhoto)
        }
    }

    companion object {
        const val POKEMON_ID_BUNDLE_KEY = "pokemon_id_bundle_key"

        fun newInstance(pokemonExpandedStatsUrl: String): Fragment {
            val bundle = Bundle()
            bundle.putString(POKEMON_ID_BUNDLE_KEY, pokemonExpandedStatsUrl)

            return PokemonExpandedStatsFragment().apply {
                arguments = bundle
            }
        }
    }
}