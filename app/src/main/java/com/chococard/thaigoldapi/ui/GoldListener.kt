package com.chococard.thaigoldapi.ui

import com.chococard.thaigoldapi.data.models.Response

interface GoldListener {
    fun onStarted()
    fun onSuccess(response: Response)
    fun onFailed(message: String)
}