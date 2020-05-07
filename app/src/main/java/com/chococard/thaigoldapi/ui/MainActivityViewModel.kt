package com.chococard.thaigoldapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chococard.thaigoldapi.data.models.Response
import com.chococard.thaigoldapi.data.repositories.GoldRepository
import com.chococard.thaigoldapi.util.ApiException
import com.chococard.thaigoldapi.util.NoInternetException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(private val repository: GoldRepository) : ViewModel(), CoroutineScope {

    private val job = Job()

    var error: ((String?) -> Unit)? = null

    private val _gold = MutableLiveData<Response>()
    val gold: LiveData<Response>
        get() = _gold

    fun fetchGold() {
        launch {
            try {
                val goldResponse = CoroutineScope(Dispatchers.IO).async {
                    repository.fetchGold()
                }.await()
                _gold.value = goldResponse.response
            } catch (e: ApiException) {
                error?.invoke(e.message)
            } catch (e: NoInternetException) {
                error?.invoke(e.message)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}
