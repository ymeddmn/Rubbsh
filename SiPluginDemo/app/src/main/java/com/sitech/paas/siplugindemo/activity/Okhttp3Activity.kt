package com.example.sitechapm.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.sitechapm.CommonAdapter
import com.example.sitechapm.log.e
import com.example.sitechapm.shouldPrint
import com.sitech.paas.siplugindemo.R
import com.sitech.paas.tracer.service.HttpService
import com.sitech.paas.tracer.timer.CycleUpload
import com.sitech.paas.tracer.utils.GSON
import kotlinx.android.synthetic.main.activity_internet.*
import kotlinx.android.synthetic.main.activity_web_view.*
import okhttp3.*
import java.io.IOException

class Okhttp3Activity : AppCompatActivity() {
    companion object {
        const val tag = "Okhttp3Activity"
    }

    var data: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet)
        btn_request_one.setOnClickListener {
            request()
        }
        btn_clear_http.setOnClickListener {
            HttpService.deleteData()
        }
        recycle_internet.layoutManager = LinearLayoutManager(this)
        recycle_internet.adapter = CommonAdapter(this, data)
        getData()
    }

    private fun getData() {
//        val eventList = CycleUpload.getHttpInfo()
//        data.clear()
//        for (e in eventList) {
//            val dataStr = e.toJson()
//            data.add(dataStr)
//        }
//        e(data)
//        recycle_internet.adapter?.notifyDataSetChanged()
    }

    private fun request() {

        val url = "http://eip.teamshub.com"
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build()
        val call = okHttpClient.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(tag, "请求失败")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                Log.e(tag, "请求成功")
            }
        })

    }
}
