package com.example.sitechapm.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.example.sitechapm.CommonAdapter
import com.sitech.paas.siplugindemo.R
import com.sitech.paas.tracer.http.SitechWebChromeClient
import com.sitech.paas.tracer.service.HttpService
import com.sitech.paas.tracer.service.WebViewService
import kotlinx.android.synthetic.main.activity_internet.*
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    private val datas: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webview.webChromeClient = WebChromeClient()
        webview.webViewClient = WebViewClient()
        webview.settings.javaScriptEnabled = true
        webview.loadUrl("https://blog.csdn.net/owenli2015/article/details/81140033")
        btn_webview_list.setOnClickListener {
            startActivity(Intent(this@WebViewActivity, WebviewListActivity::class.java))
        }
    }


}
