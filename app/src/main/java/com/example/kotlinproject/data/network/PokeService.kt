package com.example.kotlinproject.data.network

import android.util.Log
import com.example.kotlinproject.data.model.PokeResponse
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
}