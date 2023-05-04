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
import com.example.kotlinproject.domain.model.FavPokemon
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.ui.view.MainActivity
import com.example.kotlinproject.ui.view.favorites.recyclerview.PokemonFavoritesViewHolder
import com.example.kotlinproject.ui.view.home.recyclerview.PokemonHomeViewHolder
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
        //Log.i("PATATA", "lista ${PokemonHomeViewHolder.favorites.toString()}, Pokemon clickado: $pokemonItem")
    }

    fun unfavPokemon(pokemonItem: String) {
        viewModelScope.launch {
            removeFavPokemon(pokemonItem)
            MainActivity.listFavorites.remove(FavPokemon(pokemonItem, MainActivity.email!!))
        }
        //Log.i("PATATA", "lista ${PokemonHomeViewHolder.favorites.toString()}, Pokemon clickado: $pokemonItem")
    }

    fun showFavs() {
        viewModelScope.launch {
            //Log.i("PATATA", "lista ${PokemonHomeViewHolder.favorites.toString()}")
            val listFavs: MutableList<PokemonItem> = mutableListOf()
            val myFavorites = MainActivity.listFavorites.filter {it.email==MainActivity.email}
            myFavorites.forEach {
                listFavs.add(getPokemonById(it.idPokemon))
            }
            pokemonFavoritesViewModel.postValue(listFavs)
//            val result = getAllFavs()
//            pokemonFavoritesViewModel.postValue(result)
            MainActivity.listFavorites.clear()
//            result.forEach {MainActivity.listFavorites.add(FavPokemon(it.id, MainActivity.email!!)) }
        }
    }
}