package com.chococard.thaigoldapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chococard.thaigoldapi.R
import com.chococard.thaigoldapi.data.networks.GoldApi
import com.chococard.thaigoldapi.data.repositories.GoldRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = MainActivityFactory(GoldRepository(GoldApi.invoke()))
        viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)

        viewModel.getGold()
        viewModel.gold.observe(this, Observer {
            tv_date.text = it.date
            tv_update_time.text = it.updateTime
            tv_gold_buy.text = it.price?.gold?.buy
            tv_gold_sell.text = it.price?.gold?.sell
            tv_gold_bar_buy.text = it.price?.goldBar?.buy
            tv_gold_bar_sell.text = it.price?.goldBar?.sell
        })

    }
}
