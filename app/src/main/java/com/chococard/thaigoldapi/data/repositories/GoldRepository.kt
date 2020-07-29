package com.chococard.thaigoldapi.data.repositories

import com.chococard.thaigoldapi.data.networks.response.GoldResponse

interface GoldRepository {

    suspend fun fetchGold(): GoldResponse

}
