package com.example.kotlinproject.ui.view.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.domain.AddFavPokemon
import com.example.kotlinproject.domain.GetAllFavs
import com.example.kotlinproject.domain.RemoveFavPokemon
import com.example.kotlinproject.data.model.FavPokemon
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.ui.view.MainActivity
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

    fun addFavoritePokemon(pokemonItem: PokemonItem) {
        viewModelScope.launch {
            addFavPokemon(pokemonItem)
            MainActivity.listFavorites.add(FavPokemon(pokemonItem.id, MainActivity.email!!))
        }
    }

    fun unfavPokemon(pokemonItem: String) {
        viewModelScope.launch {
            removeFavPokemon(pokemonItem)
            MainActivity.listFavorites.remove(FavPokemon(pokemonItem, MainActivity.email!!))
        }
    }

    fun showFavs() {
        viewModelScope.launch {
            val result = getAllFavs()
            pokemonFavoritesViewModel.postValue(result)
            MainActivity.listFavorites.clear()
            result.forEach {MainActivity.listFavorites.add(FavPokemon(it.id, MainActivity.email!!)) }
        }
    }
}