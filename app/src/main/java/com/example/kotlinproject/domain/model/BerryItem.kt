package com.example.kotlinproject.domain.model

import com.example.kotlinproject.data.model.BerryResponse
import com.google.gson.annotations.SerializedName

data class BerryItem(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("growth_time") val growthTime: String,
    @SerializedName("max_harvest") val maxHarvest: String,
    @SerializedName("firmness") val firmness: Firmness,
    @SerializedName("flavors") val flavors: List<FlavorItem>,
    @SerializedName("natural_gift_type") val naturalGiftType: NaturalGiftType
)

data class Firmness(
    @SerializedName("name") val name: String
)
data class FlavorItem(
    @SerializedName("potency") val potency: String,
    @SerializedName("flavor") val flavor: Flavor
)
data class Flavor(
    @SerializedName("name") val name: String
)
data class NaturalGiftType(
    @SerializedName("name") val name: String
)

fun BerryResponse.toDomain() = BerryItem(id, name, growthTime, maxHarvest, firmness, flavors, naturalGiftType)