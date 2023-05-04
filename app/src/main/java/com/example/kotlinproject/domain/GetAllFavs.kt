package com.example.kotlinproject.domain

import com.example.kotlinproject.data.PokemonRepository
import com.example.kotlinproject.domain.model.PokemonItem
import javax.inject.Inject

class GetAllFavs @Inject constructor(
    private val repository: PokemonRepository
){
    suspend operator fun invoke(): List<PokemonItem> = repository.getAllFavs()

}