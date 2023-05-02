package com.example.kotlinproject.domain

import com.example.kotlinproject.data.PokemonRepository
import com.example.kotlinproject.data.database.entities.FavPokemonEntity
import javax.inject.Inject

class AddFavPokemon @Inject constructor(
    private val repository: PokemonRepository
){
    suspend operator fun invoke(favPokemon: FavPokemonEntity) = repository.addFavPokemon(favPokemon)
}