package com.chococard.thaigoldapi.data.networks

import com.chococard.thaigoldapi.data.networks.response.GoldResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://thai-gold-api.herokuapp.com/latest

interface GoldApi {

    @GET("latest")
    suspend fun getGold(): Response<GoldResponse>

    companion object {
        operator fun invoke(): GoldApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://thai-gold-api.herokuapp.com/")
                .build()
                .create(GoldApi::class.java)
        }

    }

}