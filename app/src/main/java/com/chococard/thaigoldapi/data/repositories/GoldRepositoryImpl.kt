package com.chococard.thaigoldapi.data.repositories

import com.chococard.thaigoldapi.data.networks.GoldApi
import com.chococard.thaigoldapi.data.networks.response.GoldResponse

class GoldRepositoryImpl(private val api: GoldApi) : GoldRepository {

    override suspend fun fetchGold(): GoldResponse = api.fetchGold()

}
