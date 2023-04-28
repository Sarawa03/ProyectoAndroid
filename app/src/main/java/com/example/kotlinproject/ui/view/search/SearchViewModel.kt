package com.example.kotlinproject.ui.view.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.domain.GetPokemonByName
import com.example.kotlinproject.domain.model.PokemonItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getPokemonByName: GetPokemonByName
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
}