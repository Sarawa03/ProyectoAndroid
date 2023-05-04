package com.example.kotlinproject.domain.model

import androidx.room.ColumnInfo
import com.example.kotlinproject.data.model.PokeResponse
import com.example.kotlinproject.ui.view.MainActivity
import com.google.gson.annotations.SerializedName

data class FavPokemonItem (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: String,
    @SerializedName("weight") val weight: String,
    @SerializedName("sprites") val sprites: PokeSprites,
    @SerializedName("types") val typeListItem: List<TypeListItem>,
    @SerializedName("stats") val statsList: List<StatsItem>,
    @ColumnInfo(name = "email") val email: String
    )

fun PokeResponse.toFav() = FavPokemonItem(id, name, height, weight, sprites, typeListItem, statsItem, MainActivity.email!!)