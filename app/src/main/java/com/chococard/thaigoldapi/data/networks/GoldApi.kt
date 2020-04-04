package com.chococard.thaigoldapi.data.networks

import com.chococard.thaigoldapi.data.networks.response.GoldResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://thai-gold-api.herokuapp.com/latest

interface GoldApi {

    @GET("latest")
    suspend fun fetchGold(): Response<GoldResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): GoldApi {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://thai-gold-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GoldApi::class.java)
        }
    }

}