package com.example.kotlinproject.ui.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.R
import com.example.kotlinproject.domain.GetPokemonById
import com.example.kotlinproject.domain.model.PokemonItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonById: GetPokemonById
): ViewModel(){

    val pokemonHomeViewModel = MutableLiveData<List<PokemonItem>>()
    val isLoading = MutableLiveData<Boolean>()

    fun randomPokemons(){
        viewModelScope.launch {
            isLoading.postValue(true)

            val listPokemons: MutableList<PokemonItem> = mutableListOf()
            Log.i("PATATA", listPokemons.toString())
            for(i in 0..9) listPokemons.add(getPokemonById((1..1010).random().toString()))
            Log.i("PATATA", listPokemons.toString())
            pokemonHomeViewModel.postValue(listPokemons)
        }
        isLoading.postValue(false)
    }

    fun replaceFragment(originFragment: Fragment, destinationFragment: Fragment, bundle: Bundle){
        val fragmentManager = originFragment.requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        destinationFragment.arguments = bundle
        fragmentTransaction.replace(R.id.nav_host_fragment, destinationFragment)
        fragmentTransaction.commit()
    }

}