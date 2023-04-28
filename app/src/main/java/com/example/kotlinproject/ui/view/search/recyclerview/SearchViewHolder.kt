package com.example.kotlinproject.ui.view.search.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.databinding.ItemPokemonBinding
import com.example.kotlinproject.domain.model.PokemonItem
import com.squareup.picasso.Picasso

class SearchViewHolder (view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemPokemonBinding.bind(view)

    fun bind(pokemonItem: PokemonItem, onItemSelected: (String) -> Unit){
        binding.tvPokemonName.text = pokemonItem.name
        binding.tvPokemonId.text =buildString {
            append("#")
            append(pokemonItem.id)
        }
        Picasso.get().load(pokemonItem.sprites.imgFrontM).into(binding.ivPokemon)

        binding.root.setOnClickListener { onItemSelected(pokemonItem.id) }
    }

}