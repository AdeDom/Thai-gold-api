package com.chococard.thaigoldapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chococard.thaigoldapi.data.repositories.GoldRepository

@Suppress("UNCHECKED_CAST")
class MainActivityFactory(
    private val repository: GoldRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repository) as T
    }
}
