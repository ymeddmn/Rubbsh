package com.example.sitechapm.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.sitechapm.CommonAdapter
import com.example.sitechapm.log.e
import com.example.sitechapm.shouldPrint
import com.google.gson.Gson
import com.sitech.paas.siplugindemo.R
import com.sitech.paas.tracer.service.DBCommonService
import com.sitech.paas.tracer.service.PageService
import com.sitech.paas.tracer.timer.CycleUpload
import com.sitech.paas.tracer.utils.GSON
import kotlinx.android.synthetic.main.activity_page_trace.*

class PageTraceActivity : AppCompatActivity() {
    var data: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_trace)
        initView()
        getData()
    }

    private fun initView() {
        recycle_page.layoutManager = LinearLayoutManager(this)
        recycle_page.adapter = CommonAdapter(this, data)
        btn_page_clear.setOnClickListener {
            DBCommonService.deletePageTable()
            data.clear()
            getData()
        }
    }

    private fun getData() {
//        val events = CycleUpload.getPageInfo()
//        events.forEach {
//            val toJson = GSON.toJson(it)
//            data.add(toJson)
//        }
//        e(data)
//        recycle_page.adapter?.notifyDataSetChanged()

    }
}
