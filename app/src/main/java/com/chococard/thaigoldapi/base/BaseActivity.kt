package com.chococard.thaigoldapi.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chococard.thaigoldapi.factories.KodeinViewModelFactory
import com.chococard.thaigoldapi.util.extension.toast
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

abstract class BaseActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    inline fun <reified VM : ViewModel> activityViewModel() = lazy {
        ViewModelProvider(this, KodeinViewModelFactory(kodein)).get(VM::class.java)
    }

    inline fun <reified T> LiveData<T>.observe(crossinline onNext: (T) -> Unit) {
        observe(this@BaseActivity, Observer { onNext(it) })
    }

    protected fun LiveData<Throwable>.observeError() {
        observe(this@BaseActivity, Observer {
            it.printStackTrace()
            toast("Error : ${it.message}")
        })
    }

}
