package com.example.kotlinproject.data

import android.util.Log
import com.example.kotlinproject.data.database.dao.FavPokemonDao
import com.example.kotlinproject.data.database.entities.FavPokemonEntity
import com.example.kotlinproject.data.model.PokeResponse
import com.example.kotlinproject.data.network.PokeService
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.domain.model.toDomain
import javax.inject.Inject


class PokemonRepository @Inject constructor(
    private val api: PokeService,
    private val favPokemonDao: FavPokemonDao
    ) {


    suspend fun getPokemonById(id: String): PokemonItem {
        return api.getPokemonById(id).toDomain()

    }

    suspend fun getPokemonByName(name: String): PokemonItem {
        return api.getPokemonByName(name).toDomain()
    }

    suspend fun addFavPokemon(favPokemon: FavPokemonEntity) {
        favPokemonDao.insertFavPokemon(favPokemon)
    }

    suspend fun removeFavPokemon(favPokemon: String) {
        favPokemonDao.deleteFavPokemon(favPokemon)

    }

    suspend fun getAllFavs(): List<PokemonItem> {
        Log.i("PATATA", favPokemonDao.getAllFavPokemons().toString())
        return api.getAllFavs(favPokemonDao.getAllFavPokemons().orEmpty())

    }

}