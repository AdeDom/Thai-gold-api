package com.chococard.thaigoldapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chococard.thaigoldapi.data.repositories.GoldRepository

@Suppress("UNCHECKED_CAST")
class BaseViewModelFactory(
    private val repository: GoldRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainActivityViewModel::class.java) -> MainActivityViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModel class not found...")
        }
    }
}
