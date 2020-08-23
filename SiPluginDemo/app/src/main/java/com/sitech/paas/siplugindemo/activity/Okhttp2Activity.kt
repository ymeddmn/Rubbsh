package com.example.sitechapm.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sitech.paas.siplugindemo.R
//import com.sitech.paas.tracer.http.Okhttp2
import kotlinx.android.synthetic.main.activity_okhttp2.*

class Okhttp2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp2)
        btn_okhttp2.setOnClickListener {
//            Okhttp2.hh()
        }
    }
}
