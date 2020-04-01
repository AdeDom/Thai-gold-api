package com.chococard.thaigoldapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.chococard.thaigoldapi.R
import com.chococard.thaigoldapi.data.models.Response
import com.chococard.thaigoldapi.data.networks.GoldApi
import com.chococard.thaigoldapi.data.repositories.GoldRepository
import com.chococard.thaigoldapi.util.extension.hide
import com.chococard.thaigoldapi.util.extension.show
import com.chococard.thaigoldapi.util.extension.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GoldListener {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = MainActivityFactory(GoldRepository(GoldApi.invoke()))
        viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)

        viewModel.goldListener = this

        viewModel.getGold()
//        progress_bar.show()
//        viewModel.gold.observe(this, Observer {
//            progress_bar.hide()
//            tv_date.text = it.date
//            tv_update_time.text = it.updateTime
//            tv_gold_buy.text = it.price?.gold?.buy
//            tv_gold_sell.text = it.price?.gold?.sell
//            tv_gold_bar_buy.text = it.price?.goldBar?.buy
//            tv_gold_bar_sell.text = it.price?.goldBar?.sell
//        })

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(response: Response) {
        progress_bar.hide()
        tv_date.text = response.date
        tv_update_time.text = response.updateTime
        tv_gold_buy.text = response.price?.gold?.buy
        tv_gold_sell.text = response.price?.gold?.sell
        tv_gold_bar_buy.text = response.price?.goldBar?.buy
        tv_gold_bar_sell.text = response.price?.goldBar?.sell
    }

    override fun onFailed(message: String) {
        progress_bar.hide()
        toast(message)
    }
}
