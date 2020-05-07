package com.chococard.thaigoldapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chococard.thaigoldapi.R
import com.chococard.thaigoldapi.util.extension.hide
import com.chococard.thaigoldapi.util.extension.show
import com.chococard.thaigoldapi.util.extension.toast
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory: MainActivityFactory by instance()
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

        viewModel.error = { message ->
            progress_bar.hide()
            message?.let { toast(it) }
        }

    }

}
