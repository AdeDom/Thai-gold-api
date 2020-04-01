package com.chococard.thaigoldapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chococard.thaigoldapi.data.models.Response
import com.chococard.thaigoldapi.data.networks.response.GoldResponse
import com.chococard.thaigoldapi.data.repositories.GoldRepository
import com.chococard.thaigoldapi.util.ApiException
import com.chococard.thaigoldapi.util.Coroutines
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

class MainActivityViewModel(private val repository: GoldRepository) : ViewModel() {

    private lateinit var job: Job

    private val _gold = MutableLiveData<Response>()
    val gold: LiveData<Response>
        get() = _gold

    fun getGold() {
        job = Coroutines.main {
            val goldResponse = repository.getGold()
            goldResponse.response?.let {
                _gold.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

}