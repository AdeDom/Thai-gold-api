package com.chococard.thaigoldapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chococard.thaigoldapi.data.models.Response
import com.chococard.thaigoldapi.data.repositories.GoldRepository
import com.chococard.thaigoldapi.util.ApiException
import com.chococard.thaigoldapi.util.Coroutines
import com.chococard.thaigoldapi.util.NoInternetException
import kotlinx.coroutines.Job

class MainActivityViewModel(private val repository: GoldRepository) : ViewModel() {

    private lateinit var job: Job

    var exception: ((String) -> Unit)? = null

    private val _gold = MutableLiveData<Response>()
    val gold: LiveData<Response>
        get() = _gold

    fun fetchGold() {
        job = Coroutines.main {
            try {
                val goldResponse = repository.fetchGold()
                goldResponse.response?.let {
                    _gold.value = it
                }
            } catch (e: ApiException) {
                exception?.invoke(e.message!!)
            } catch (e: NoInternetException) {
                exception?.invoke(e.message!!)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

}