package com.example.kotlinproject.domain

import com.example.kotlinproject.data.PokemonRepository
import javax.inject.Inject

class GetPokemonById @Inject constructor(private val repository: PokemonRepository){

    suspend operator fun invoke(id: String) = repository.getPokemonById(id)

}