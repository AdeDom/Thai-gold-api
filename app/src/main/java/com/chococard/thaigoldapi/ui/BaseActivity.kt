package com.chococard.thaigoldapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

abstract class BaseActivity<VM : ViewModel> : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory by instance<BaseViewModelFactory>()
        viewModel = ViewModelProvider(this, factory).get(viewModel())
    }

    abstract fun viewModel(): Class<VM>

}
