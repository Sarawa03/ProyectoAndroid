package com.example.kotlinproject.data

import com.example.kotlinproject.data.model.PokeResponse
import com.example.kotlinproject.data.network.PokeService
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.domain.model.toDomain
import javax.inject.Inject


class PokemonRepository @Inject constructor(private val api: PokeService){


    suspend fun getPokemonById(id: String): PokemonItem{
        return api.getPokemonById(id).toDomain()

    }

    suspend fun getPokemonByName(name: String): PokemonItem {
        return api.getPokemonByName(name).toDomain()
    }

}