package com.example.kotlinproject.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.ui.view.MainActivity

@Entity(tableName = "pokemon_table")
data class FavPokemonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "idPokemon") val idPokemon: String,
    @ColumnInfo(name = "email") val email: String
)

fun PokemonItem.toEntityId() = FavPokemonEntity(idPokemon = id, email = MainActivity.email!!)