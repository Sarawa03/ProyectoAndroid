package com.example.kotlinproject.ui.view.favorites.recyclerview

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ItemPokemonBinding
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.ui.view.MainActivity
import com.example.kotlinproject.ui.view.home.recyclerview.PokemonHomeViewHolder
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class PokemonFavoritesViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    fun bind(pokemonItem: PokemonItem, onItemSelected: (String) -> Unit, addFavPokemon: (PokemonItem) -> Unit, unfavPokemon: (String) -> Unit){
        binding.tvPokemonName.text = pokemonItem.name

        val dec = DecimalFormat("0000")

        binding.tvPokemonId.text = buildString {
            append("#")
            append(dec.format(pokemonItem.id.toInt()))
        }
        Picasso.get().load(pokemonItem.sprites.imgFrontM).into(binding.ivPokemon)

        if(containsPokemonItem(pokemonItem))binding.favIcon.setImageResource(R.drawable.ic_favorites_enabled)
        else binding.favIcon.setImageResource(R.drawable.ic_favorite_disabled)

        binding.favIcon.setOnClickListener {
            if(containsPokemonItem(pokemonItem)){
                binding.favIcon.setImageResource(R.drawable.ic_favorite_disabled)
                unfavPokemon(pokemonItem.id)
            }else{
                binding.favIcon.setImageResource(R.drawable.ic_favorites_enabled)
                addFavPokemon(pokemonItem)
            }

        }

        binding.root.setOnClickListener { onItemSelected(pokemonItem.id) }
    }

    private fun containsPokemonItem(pokemonItem: PokemonItem): Boolean{
        val myList = MainActivity.listFavorites.filter { it.email==MainActivity.email }
        myList.forEach {
            if(it.idPokemon==pokemonItem.id) { return true }
        }
        return false
    }
}