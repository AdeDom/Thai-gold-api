package com.chococard.thaigoldapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chococard.thaigoldapi.R
import com.chococard.thaigoldapi.data.networks.GoldApi
import com.chococard.thaigoldapi.data.networks.NetworkConnectionInterceptor
import com.chococard.thaigoldapi.data.repositories.GoldRepository
import com.chococard.thaigoldapi.util.extension.hide
import com.chococard.thaigoldapi.util.extension.show
import com.chococard.thaigoldapi.util.extension.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val interceptor = NetworkConnectionInterceptor(baseContext)
        val factory = MainActivityFactory(GoldRepository(GoldApi.invoke(interceptor)))
        viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)

        viewModel.fetchGold()
        progress_bar.show()
        viewModel.gold.observe(this, Observer {
            progress_bar.hide()
            tv_date.text = it.date
            tv_update_time.text = it.updateTime
            tv_gold_buy.text = it.price?.gold?.buy
            tv_gold_sell.text = it.price?.gold?.sell
            tv_gold_bar_buy.text = it.price?.goldBar?.buy
            tv_gold_bar_sell.text = it.price?.goldBar?.sell
        })

        viewModel.exception = {
            progress_bar.hide()
            toast(it)
        }

    }

}
