package com.example.kotlinproject.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.domain.GetPokemonById
import com.example.kotlinproject.domain.model.PokemonItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPokemonById: GetPokemonById
): ViewModel(){

    val pokemonDetailsModel = MutableLiveData<PokemonItem>()

    fun postPokemonDetails(id: String){
        viewModelScope.launch {
            val result = getPokemonById(id)
            pokemonDetailsModel.postValue(result)
        }
    }

}