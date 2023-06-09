package com.example.kotlinproject.domain

import com.example.kotlinproject.data.PokemonRepository
import javax.inject.Inject

class GetPokemonByName @Inject constructor(private val repository: PokemonRepository){
    suspend operator fun invoke(name: String) = repository.getPokemonByName(name)
}