package com.chococard.thaigoldapi.ui

import com.chococard.thaigoldapi.base.BaseViewModel
import com.chococard.thaigoldapi.data.models.Response
import com.chococard.thaigoldapi.data.repositories.GoldRepository
import kotlinx.coroutines.launch

data class MainViewState(
    val data: Response? = Response(),
    val loading: Boolean = false
)

class MainViewModel(
    private val repository: GoldRepository
) : BaseViewModel<MainViewState>(MainViewState()) {

    fun fetchGold() {
        launch {
            try {
                setState { copy(loading = true) }
                val response = repository.fetchGold()
                setState { copy(loading = false, data = response.response) }
            } catch (e: Throwable) {
                setState { copy(loading = false) }
                setError(e)
            }
        }
    }

}
