package ru.androiddev.pokemon.presentation.feature.pokemon.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_pokemon.*
import ru.androiddev.pokemon.R
import ru.androiddev.pokemon.presentation.base.BaseFragment
import ru.androiddev.pokemon.presentation.feature.pokemon.list.PokemonAdapter
import ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel.PokemonViewModel
import ru.androiddev.pokemon.presentation.feature.pokemon.viewmodel.PokemonViewModelFactory

class PokemonFragment : BaseFragment<PokemonViewModelFactory, PokemonViewModel>() {

    private var adapter: PokemonAdapter? = null
    private var layoutManager: LinearLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = PokemonAdapter()
        fragmentPokemonList.adapter = adapter
        fragmentPokemonList.layoutManager = LinearLayoutManager(requireContext())

        layoutManager = fragmentPokemonList.layoutManager as LinearLayoutManager

        initLiveData()

        fragmentPokemonList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val itemCount = layoutManager?.itemCount ?: 0
                val visibleCount = layoutManager?.childCount ?: 0
                val firstVisible = layoutManager?.findFirstVisibleItemPosition() ?: 0

                if (visibleCount + firstVisible >= itemCount) {
                    viewModel.getPokemons()
                }
            }
        })

        viewModel.getPokemons()
    }

    override fun initViewModel() {
        setViewModel(PokemonViewModel::class.java)
    }

    override fun onDestroy() {
        viewModel.unsubscribe()
        super.onDestroy()
    }

    private fun initLiveData() {
        viewModel.itemsLiveData.observe(viewLifecycleOwner) {
            adapter?.addData(it)
        }
    }
}