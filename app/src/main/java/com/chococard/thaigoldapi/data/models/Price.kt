package com.chococard.thaigoldapi.data.models

import com.google.gson.annotations.SerializedName

data class Price(
    val gold: Gold? = null,
    @SerializedName("gold_bar") val goldBar: GoldBar? = null
)