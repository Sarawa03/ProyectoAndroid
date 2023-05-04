package com.example.kotlinproject.domain

import com.example.kotlinproject.data.PokemonRepository
import javax.inject.Inject

class RemoveFavPokemon @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(favPokemon: String) = repository.removeFavPokemon(favPokemon)
}