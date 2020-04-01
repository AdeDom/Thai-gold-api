package com.chococard.thaigoldapi.ui

import androidx.lifecycle.ViewModel
import com.chococard.thaigoldapi.data.repositories.GoldRepository
import com.chococard.thaigoldapi.util.ApiException
import com.chococard.thaigoldapi.util.Coroutines
import kotlinx.coroutines.Job

class MainActivityViewModel(private val repository: GoldRepository) : ViewModel() {

    private lateinit var job: Job
    var goldListener: GoldListener? = null

//    private val _gold = MutableLiveData<Response>()
//    val gold: LiveData<Response>
//        get() = _gold

    fun getGold() {
        goldListener?.onStarted()
        job = Coroutines.main {
            try {
                val goldResponse = repository.getGold()
                goldResponse.response?.let {
//                    _gold.value = it
                    goldListener?.onSuccess(it)
                }
            } catch (e: ApiException) {
                goldListener?.onFailed(e.message!!)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

}