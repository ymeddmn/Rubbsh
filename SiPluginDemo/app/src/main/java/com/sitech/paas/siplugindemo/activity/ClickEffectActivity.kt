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
import com.sitech.paas.tracer.service.TrackerService
import com.sitech.paas.tracer.timer.CycleUpload
import com.sitech.paas.tracer.utils.CLog
import com.sitech.paas.tracer.utils.GSON
import com.sitech.paas.tracer.utils.showToast
import kotlinx.android.synthetic.main.activity_click_effect.*
import kotlin.collections.ArrayList

class ClickEffectActivity : AppCompatActivity() {
    var data: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_effect)
        recycle.layoutManager = LinearLayoutManager(this)
        recycle.adapter = CommonAdapter(this, data)
        btn_clear_click.setOnClickListener {
            DBCommonService.deleteClickTable()
            data.clear()
            getData()
        }
        btn_add_click.setOnClickListener {
            showToast(this@ClickEffectActivity, "增加一条点击事件")
            getData()
        }
        getData()

    }

    private fun getData() {
//        val events = CycleUpload.getActiveInfo()
//        data.clear()
//        events.forEach {
//            val json = GSON.toJson(it)
//            data.add(json)
//        }
//        e(data)
//        recycle.adapter?.notifyDataSetChanged()
    }
}
