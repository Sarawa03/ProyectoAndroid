package com.example.kotlinproject.ui.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.domain.AddFavPokemon
import com.example.kotlinproject.domain.GetPokemonById
import com.example.kotlinproject.domain.RemoveFavPokemon
import com.example.kotlinproject.data.model.FavPokemon
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.ui.view.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonById: GetPokemonById,
    private val addFavPokemon: AddFavPokemon,
    private val removeFavPokemon: RemoveFavPokemon
): ViewModel(){

    val pokemonHomeViewModel = MutableLiveData<List<PokemonItem>>()

    fun randomPokemons(){
        viewModelScope.launch {
            val listRandomPokemon: MutableList<Int> = mutableListOf()
            for (i in 0..9){
                var random =(1..1010).random()
                while (listRandomPokemon.contains(random)) random=(1..1010).random()
                listRandomPokemon.add(random)
            }

            val listPokemons: MutableList<PokemonItem> = mutableListOf()
            listRandomPokemon.forEach{listPokemons.add(getPokemonById(it.toString()))}
            pokemonHomeViewModel.postValue(listPokemons)

        }
    }

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


}