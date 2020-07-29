package com.chococard.thaigoldapi.data.networks

import com.chococard.thaigoldapi.data.networks.response.GoldResponse
import retrofit2.http.GET

interface GoldApi {

    @GET("latest")
    suspend fun fetchGold(): GoldResponse

}
