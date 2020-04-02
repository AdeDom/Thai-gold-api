package com.chococard.thaigoldapi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chococard.thaigoldapi.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        bt_call_api.setOnClickListener {
            Intent(baseContext, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}
