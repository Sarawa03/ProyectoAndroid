package com.example.kotlinproject.data.network

import com.example.kotlinproject.data.model.PokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/pokemon/{name}/")
    suspend fun getPokemon(@Path("name")pokemonIdOrName: String): Response<PokeResponse>

}