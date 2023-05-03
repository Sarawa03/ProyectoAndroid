package com.example.kotlinproject.data.model

import com.example.kotlinproject.domain.model.Firmness
import com.example.kotlinproject.domain.model.FlavorItem
import com.example.kotlinproject.domain.model.NaturalGiftType
import com.google.gson.annotations.SerializedName

data class BerryResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("growth_time") val growthTime: String,
    @SerializedName("max_harvest") val maxHarvest: String,
    @SerializedName("firmness") val firmness: Firmness,
    @SerializedName("flavors") val flavors: List<FlavorItem>,
    @SerializedName("natural_gift_type") val naturalGiftType: NaturalGiftType
)