package com.chococard.thaigoldapi

import android.app.Application
import com.chococard.thaigoldapi.data.networks.GoldApi
import com.chococard.thaigoldapi.data.networks.NetworkConnectionInterceptor
import com.chococard.thaigoldapi.data.repositories.GoldRepository
import com.chococard.thaigoldapi.data.repositories.GoldRepositoryImpl
import com.chococard.thaigoldapi.ui.MainViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MainApplication))

        bind<Interceptor>() with provider { NetworkConnectionInterceptor(instance()) }

        bind<OkHttpClient>() with provider {
            OkHttpClient.Builder().apply {
                addInterceptor(instance())
            }.build()
        }

        bind<String>("base_url") with provider { "https://thai-gold-api.herokuapp.com/" }

        bind<Retrofit>() with provider {
            Retrofit.Builder()
                    .client(instance())
                    .baseUrl(instance<String>("base_url"))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        bind<GoldApi>() with provider { instance<Retrofit>().create(GoldApi::class.java) }

        bind<GoldRepository>() with provider { GoldRepositoryImpl(instance()) }

        bind() from provider { MainViewModel(instance()) }
    }

}
