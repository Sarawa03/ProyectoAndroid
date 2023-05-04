package com.example.kotlinproject.data

import com.example.kotlinproject.data.database.dao.FavPokemonDao
import com.example.kotlinproject.data.database.entities.toEntityId
import com.example.kotlinproject.data.network.PokeService
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.domain.model.toDomain
import com.example.kotlinproject.ui.view.MainActivity
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

    suspend fun addFavPokemon(favPokemon: PokemonItem) {

        favPokemonDao.insertFavPokemon(favPokemon.toEntityId())

    }

    suspend fun removeFavPokemon(favPokemon: String) {
        favPokemonDao.deleteFavPokemon(favPokemon)

    }

    suspend fun getAllFavs(): List<PokemonItem> {
        val list = favPokemonDao.getAllFavPokemons().orEmpty().filter { it.email== MainActivity.email }

        return api.getAllFavs(list)

    }


}