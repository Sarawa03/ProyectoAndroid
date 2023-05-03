package com.example.kotlinproject.ui.view.favorites

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.data.database.entities.toEntityId
import com.example.kotlinproject.domain.AddFavPokemon
import com.example.kotlinproject.domain.GetAllFavs
import com.example.kotlinproject.domain.GetPokemonById
import com.example.kotlinproject.domain.RemoveFavPokemon
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.ui.view.favorites.recyclerview.PokemonFavoritesViewHolder
import com.example.kotlinproject.ui.view.home.recyclerview.PokemonHomeViewHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val addFavPokemon: AddFavPokemon,
    private val removeFavPokemon: RemoveFavPokemon,
    private val getAllFavs: GetAllFavs
): ViewModel(){
    val pokemonFavoritesViewModel = MutableLiveData<List<PokemonItem>>()

    fun addFavPokemon(pokemonItem: PokemonItem, email: String) {
        viewModelScope.launch {
            addFavPokemon(pokemonItem, email)
            PokemonHomeViewHolder.favorites.add(pokemonItem.id)
        }
        Log.i("PATATA", "lista ${PokemonHomeViewHolder.favorites.toString()}, Pokemon clickado: $pokemonItem")
    }

    fun unfavPokemon(pokemonItem: String) {
        viewModelScope.launch {
            removeFavPokemon(pokemonItem)
            PokemonHomeViewHolder.favorites.remove(pokemonItem)
        }
        Log.i("PATATA", "lista ${PokemonHomeViewHolder.favorites.toString()}, Pokemon clickado: $pokemonItem")
    }

    fun showFavs() {
        viewModelScope.launch {
            Log.i("PATATA", "lista ${PokemonHomeViewHolder.favorites.toString()}")
            val result = getAllFavs()
            pokemonFavoritesViewModel.postValue(result)
            PokemonHomeViewHolder.favorites.clear()
            result.forEach {PokemonHomeViewHolder.favorites.add(it.id) }
        }
    }
}