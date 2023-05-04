package com.example.kotlinproject.ui.view.favorites

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.domain.AddFavPokemon
import com.example.kotlinproject.domain.GetAllFavs
import com.example.kotlinproject.domain.GetPokemonById
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
    private val getPokemonById: GetPokemonById,
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

        /*viewModelScope.launch {
            Log.i("PATATA", "lista ${PokemonHomeViewHolder.favorites.toString()}")
            val result = getAllFavs()
            pokemonFavoritesViewModel.postValue(result)
            PokemonHomeViewHolder.favorites.clear()
            result.forEach {PokemonHomeViewHolder.favorites.add(it.id) }
        }*/
        viewModelScope.launch {
            val result = getAllFavs()
            pokemonFavoritesViewModel.postValue(result)
            MainActivity.listFavorites.clear()
            result.forEach {MainActivity.listFavorites.add(FavPokemon(it.id, MainActivity.email!!)) }

//            val listFavs: MutableList<PokemonItem> = mutableListOf()
//            Log.i("CONTAINSDEBUG", "showfavs list: ${MainActivity.listFavorites}")
//            val myFavorites = MainActivity.listFavorites.filter {it.email==MainActivity.email}
//            myFavorites.map {
//                listFavs.add(getPokemonById(it.idPokemon))
//            }
//            pokemonFavoritesViewModel.postValue(listFavs)
            //MainActivity.listFavorites.clear()
        }
    }
}