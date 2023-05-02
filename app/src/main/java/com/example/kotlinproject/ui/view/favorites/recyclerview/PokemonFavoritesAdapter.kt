package com.example.kotlinproject.ui.view.favorites.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.ui.view.home.recyclerview.PokemonHomeViewHolder
import javax.inject.Inject

class PokemonFavoritesAdapter @Inject constructor(
    private val onItemSelected: (String) -> Unit,
    private val addFavPokemon: (PokemonItem) -> Unit,
    private val unfavPokemon: (String) -> Unit
):
    RecyclerView.Adapter<PokemonFavoritesViewHolder>(){

    var pokemonList: List<PokemonItem> = emptyList()

    fun updateList(pokemonList: List<PokemonItem>) {
        this.pokemonList = pokemonList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonFavoritesViewHolder {
        return PokemonFavoritesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonFavoritesViewHolder, position: Int) {
        holder.bind(pokemonList[position], onItemSelected, addFavPokemon, unfavPokemon)
    }
}