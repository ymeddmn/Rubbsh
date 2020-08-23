package com.example.sitechapm.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.sitechapm.CommonAdapter
import com.example.sitechapm.log.e
import com.example.sitechapm.shouldPrint
import com.sitech.paas.siplugindemo.R
import com.sitech.paas.tracer.service.WebViewService
import com.sitech.paas.tracer.timer.CycleUpload
import com.sitech.paas.tracer.utils.GSON
import kotlinx.android.synthetic.main.activity_webview_list.*

class WebviewListActivity : AppCompatActivity() {
    private val datas: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_list)
        initView()
    }

    private fun initView() {
        recycle_webview.layoutManager = LinearLayoutManager(this)
        recycle_webview.adapter = CommonAdapter(this, datas)
        val eventList = CycleUpload.getWebviewInfo()
        for (e in eventList) {
            val dataStr = e.toString()
            datas.add(dataStr)
        }
        e(datas)
        recycle_webview.adapter?.notifyDataSetChanged()
    }
}
