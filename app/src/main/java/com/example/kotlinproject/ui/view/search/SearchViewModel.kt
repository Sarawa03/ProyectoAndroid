package com.example.kotlinproject.ui.view.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.domain.AddFavPokemon
import com.example.kotlinproject.domain.GetPokemonByName
import com.example.kotlinproject.domain.RemoveFavPokemon
import com.example.kotlinproject.data.model.FavPokemon
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.ui.view.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getPokemonByName: GetPokemonByName,
    private val addFavPokemon: AddFavPokemon,
    private val removeFavPokemon: RemoveFavPokemon
): ViewModel(){

    val searchViewModel = MutableLiveData<List<PokemonItem>>()
    val isLoading = MutableLiveData<Boolean>()
    fun searchPokemonByName(name: String) {
        viewModelScope.launch {
            val result = getPokemonByName(name)
            var response = mutableListOf<PokemonItem>()

            if(result.name=="error"){
                searchViewModel.postValue(emptyList())
            }else{
                response.add(getPokemonByName(name))
                searchViewModel.postValue(response)
            }
        }
    }

    fun addFavoritePokemon(pokemonItem: PokemonItem) {
        viewModelScope.launch {
            addFavPokemon(pokemonItem)
            MainActivity.listFavorites.add(FavPokemon(pokemonItem.id, MainActivity.email!!))
        }
        //Log.i("PATATA", "Pokemon clickado: $pokemonItem, lista ${PokemonHomeViewHolder.favorites.toString()}")
    }

    fun unfavPokemon(pokemonItem: String) {
        viewModelScope.launch {
            removeFavPokemon(pokemonItem)
            MainActivity.listFavorites.remove(FavPokemon(pokemonItem, MainActivity.email!!))
        }
        //Log.i("PATATA", "Pokemon clickado: $pokemonItem, lista ${PokemonHomeViewHolder.favorites.toString()}")
    }
}