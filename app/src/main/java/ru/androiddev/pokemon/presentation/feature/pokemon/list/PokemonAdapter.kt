package ru.androiddev.pokemon.presentation.feature.pokemon.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.androiddev.pokemon.databinding.ItemPokemonBinding

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    val data: MutableList<PokemonItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        with(holder.viewBinding) {
            val model = data[position]
            itemPokemonName.text = data[position].name

            root.setOnClickListener { model.onClick?.invoke(model.url) }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addData(data: List<PokemonItem>) {
        val currentPosition = this.data.size
        val newPosition = currentPosition + data.size
        this.data.addAll(data)
        notifyItemRangeInserted(newPosition - 1, data.size)
    }

    inner class PokemonViewHolder(val viewBinding: ItemPokemonBinding)
        : RecyclerView.ViewHolder(viewBinding.root)
}