package com.example.sitechapm.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sitech.paas.siplugindemo.R
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_crash.setOnClickListener {
            startActivity(Intent(this@HomeActivity, CrashActivity::class.java))
        }
        btn_click.setOnClickListener {
            startActivity(Intent(this@HomeActivity, ClickEffectActivity::class.java))
        }
        btn_trace.setOnClickListener {
            startActivity(Intent(this@HomeActivity, PageTraceActivity::class.java))
        }
        btn_device_ifno.setOnClickListener {
            startActivity(Intent(this@HomeActivity, DeviceInfoActivity::class.java))
        }
        btn_internet.setOnClickListener {
            startActivity(Intent(this@HomeActivity, Okhttp3Activity::class.java))
        }

        btn_database.setOnClickListener {
            startActivity(Intent(this@HomeActivity, DatabaseActivity::class.java))
        }
        btn_okhttp2.setOnClickListener {
            startActivity(Intent(this@HomeActivity, Okhttp2Activity::class.java))
        }
        btn_httpurlconnection.setOnClickListener {
            startActivity(Intent(this@HomeActivity,HttpUrlConnectionActivity::class.java))
        }
        btn_webview.setOnClickListener {
            startActivity(Intent(this@HomeActivity,WebViewActivity::class.java))
        }
    }
}