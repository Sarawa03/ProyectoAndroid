package com.example.kotlinproject.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonItem(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: String,
    @SerializedName("weight") val weight: String,
    @SerializedName("sprites") val sprites: PokeSprites,
    @SerializedName("types") val typeListItem: List<TypeListItem>,
    @SerializedName("stats") val statsItem: List<StatsItem>,

    )

//Images
data class PokeSprites(
    @SerializedName("front_default") val imgFrontM: String,
)

//Types
data class TypeListItem(
    @SerializedName("type") val type: TypeItem,
)

data class TypeItem(
    @SerializedName("name") val name: String,
)

//Stats
data class StatsItem(
    @SerializedName("base_stat") val baseStat: String,
    @SerializedName("stat") val statName: StatName
)

data class StatName(
    @SerializedName("name") val name: String
)