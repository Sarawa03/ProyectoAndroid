package com.example.kotlinproject.data.model

import com.example.kotlinproject.domain.model.PokeSprites
import com.example.kotlinproject.domain.model.StatsItem
import com.example.kotlinproject.domain.model.TypeListItem
import com.google.gson.annotations.SerializedName

data class FavPokeResponse (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: String,
    @SerializedName("weight") val weight: String,
    @SerializedName("sprites") val sprites: PokeSprites,
    @SerializedName("types") val typeListItem: List<TypeListItem>,
    @SerializedName("stats") val statsItem: List<StatsItem>,

    )