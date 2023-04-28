package com.example.kotlinproject.ui.view.search.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.domain.model.PokemonItem
import javax.inject.Inject

class SearchAdapter @Inject constructor(private val onItemSelected: (String) -> Unit) :
    RecyclerView.Adapter<SearchViewHolder>() {

    var pokemonList: List<PokemonItem> = emptyList()

    fun updateList(pokemonList: List<PokemonItem>) {
        this.pokemonList = pokemonList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(pokemonList[position], onItemSelected)
    }
}