package com.chococard.thaigoldapi.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.chococard.thaigoldapi.R
import com.chococard.thaigoldapi.util.extension.hide
import com.chococard.thaigoldapi.util.extension.show
import com.chococard.thaigoldapi.util.extension.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainActivityViewModel>() {

    override fun viewModel() = MainActivityViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        viewModel.error = { message ->
            progress_bar.hide()
            message?.let { toast(it) }
        }
    }

}
