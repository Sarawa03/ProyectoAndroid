package com.example.kotlinproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlinproject.data.database.dao.FavPokemonDao
import com.example.kotlinproject.data.database.entities.FavPokemonEntity

@Database(entities = [FavPokemonEntity::class], version = 1)
abstract class FavPokemonDatabase : RoomDatabase(){

    abstract fun getFavPokemonDao(): FavPokemonDao
}