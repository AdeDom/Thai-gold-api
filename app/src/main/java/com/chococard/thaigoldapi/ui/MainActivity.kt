package com.chococard.thaigoldapi.ui

import android.os.Bundle
import com.chococard.thaigoldapi.R
import com.chococard.thaigoldapi.base.BaseActivity
import com.chococard.thaigoldapi.util.extension.hide
import com.chococard.thaigoldapi.util.extension.show
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    val viewModel by activityViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.state.observe { state ->
            if (state.loading) progress_bar.show() else progress_bar.hide()

            tv_date.text = state.data?.date
            tv_update_time.text = state.data?.updateTime
            tv_gold_buy.text = state.data?.price?.gold?.buy
            tv_gold_sell.text = state.data?.price?.gold?.sell
            tv_gold_bar_buy.text = state.data?.price?.goldBar?.buy
            tv_gold_bar_sell.text = state.data?.price?.goldBar?.sell
        }

        viewModel.attachFirstTime.observe {
            if (savedInstanceState == null) viewModel.fetchGold()
        }

        viewModel.error.observeError()
    }

}
