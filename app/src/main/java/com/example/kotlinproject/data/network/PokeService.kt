package com.example.kotlinproject.data.network

import android.util.Log
import com.example.kotlinproject.data.database.entities.FavPokemonEntity
import com.example.kotlinproject.data.model.BerryResponse
import com.example.kotlinproject.data.model.PokeResponse
import com.example.kotlinproject.domain.model.Firmness
import com.example.kotlinproject.domain.model.Flavor
import com.example.kotlinproject.domain.model.FlavorItem
import com.example.kotlinproject.domain.model.NaturalGiftType
import com.example.kotlinproject.domain.model.PokeSprites
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.domain.model.StatName
import com.example.kotlinproject.domain.model.StatsItem
import com.example.kotlinproject.domain.model.TypeItem
import com.example.kotlinproject.domain.model.TypeListItem
import com.example.kotlinproject.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokeService @Inject constructor(private val api: ApiService) {


    suspend fun getPokemonById(id: String): PokeResponse {
        return withContext(Dispatchers.IO){
            val response = api.getPokemonById(id)
            Log.i("PATATA", id)
            Log.i("PATATA", response.toString())
            response.body()!!
        }
    }

    suspend fun getPokemonByName(name: String): PokeResponse {
        return withContext(Dispatchers.IO){
            val response = api.getPokemonByName(name)
            Log.i("PATATA", api.getPokemonByName(name).toString())
            response.body()?: defaultPokemon()
        }
    }

    suspend fun getAllFavs(favorites: List<FavPokemonEntity>): List<PokemonItem> {
        val result: MutableList<PokemonItem> = mutableListOf()
        favorites.forEach { result.add(api.getPokemonById(it.idPokemon).body()!!.toDomain()) }
        return result
    }

    suspend fun getBerryByIdOrName(name: String): BerryResponse {
        return withContext(Dispatchers.IO){
            val response = api.getBerryByName(name)
            response.body()?: defaultBerry()
        }
    }

    private fun defaultBerry(): BerryResponse {
        return BerryResponse(
            "1",
            "cheri",
            "3",
            "5",
            Firmness("soft"),
            listOf(FlavorItem("10", Flavor("spicy"))),
            NaturalGiftType("fire")
        )
    }


    fun defaultPokemon(): PokeResponse{
        return PokeResponse(
            "1",
            "error",
            "7",
            "69",
            sprites = PokeSprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
            listOf(
                TypeListItem(TypeItem("grass")),
                TypeListItem(TypeItem("poison"))
            ),
            listOf(
                StatsItem("45", StatName("hp")),
                StatsItem("49", StatName("attack")),
                StatsItem("49", StatName("defense")),
                StatsItem("65", StatName("special-attack")),
                StatsItem("65", StatName("special-defense")),
                StatsItem("45", StatName("speed")),
            )

        )
    }

}

