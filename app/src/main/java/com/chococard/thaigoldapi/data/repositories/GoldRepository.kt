package com.chococard.thaigoldapi.data.repositories

import com.chococard.thaigoldapi.data.networks.GoldApi
import com.chococard.thaigoldapi.data.networks.SafeApiRequest

class GoldRepository(private val api: GoldApi) : SafeApiRequest() {

    suspend fun fetchGold() = apiRequest { api.fetchGold() }

}