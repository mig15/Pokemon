package ru.androiddev.pokemon.presentation.feature.pokemon.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.androiddev.pokemon.R
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    val data: MutableList<PokemonItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        with(holder.itemView) {
            val model = data[position]
            itemPokemonName.text = data[position].name

            setOnClickListener { model.onClick?.invoke(model.url) }
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

    inner class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view)
}