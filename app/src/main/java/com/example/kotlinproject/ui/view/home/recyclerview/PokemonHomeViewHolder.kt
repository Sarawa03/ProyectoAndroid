package com.example.kotlinproject.ui.view.home.recyclerview

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.databinding.ItemPokemonBinding
import com.example.kotlinproject.domain.model.PokemonItem
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class PokemonHomeViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    fun bind(pokemonItem: PokemonItem, onItemSelected: (String) -> Unit){
        Log.i("PATATA", pokemonItem.toString())
        binding.tvPokemonName.text = pokemonItem.name

        val dec = DecimalFormat("0000")

        binding.tvPokemonId.text = buildString {
            append("#")
            append(dec.format(pokemonItem.id.toInt()))
        }
        Picasso.get().load(pokemonItem.sprites.imgFrontM).into(binding.ivPokemon)

        binding.root.setOnClickListener { onItemSelected(pokemonItem.id) }
        //TODO que pase también donde clickó a la lambda y así no hay que pasar tantas lambdas y se puede hacer toda desde una
    }

}