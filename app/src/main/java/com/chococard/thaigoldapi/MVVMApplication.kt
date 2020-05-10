package com.chococard.thaigoldapi

import android.app.Application
import com.chococard.thaigoldapi.data.networks.GoldApi
import com.chococard.thaigoldapi.data.networks.NetworkConnectionInterceptor
import com.chococard.thaigoldapi.data.repositories.GoldRepository
import com.chococard.thaigoldapi.ui.BaseViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { GoldApi(instance()) }
        bind() from singleton { GoldRepository(instance()) }
        bind() from provider { BaseViewModelFactory(instance()) }
    }

}
