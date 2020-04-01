package com.chococard.thaigoldapi.data.models

import com.google.gson.annotations.SerializedName

data class Response(
    val date: String? = null,
    val price: Price? = null,
    @SerializedName("update_time") val updateTime: String? = null
)