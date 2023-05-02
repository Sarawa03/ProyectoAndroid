package com.example.kotlinproject.di

import android.content.Context
import androidx.room.Room
import com.example.kotlinproject.data.database.FavPokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DB_NAME = "PokemonDB"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, FavPokemonDatabase::class.java, DB_NAME).build()

    @Singleton
    @Provides
    fun provideFavPokemonDao(database: FavPokemonDatabase) = database.getFavPokemonDao()

}