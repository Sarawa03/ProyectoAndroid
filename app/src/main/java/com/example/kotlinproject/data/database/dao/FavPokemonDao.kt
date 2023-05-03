package com.example.kotlinproject.data.database.dao

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinproject.data.database.entities.FavPokemonEntity

@Dao
interface FavPokemonDao {

    @Query("SELECT * FROM pokemon_table")
    suspend fun getAllFavPokemons(): List<FavPokemonEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavPokemon(favPokemonId: FavPokemonEntity)

    @Query("DELETE FROM pokemon_table WHERE idPokemon = :favPokemonId")
    suspend fun deleteFavPokemon(favPokemonId: String)

    @Query("DELETE FROM pokemon_table")
    suspend fun deleteAllFavs()


}