package com.example.sitechapm.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.sitechapm.CommonAdapter
import com.example.sitechapm.log.e
import com.example.sitechapm.shouldPrint
import com.sitech.paas.siplugindemo.R
import com.sitech.paas.tracer.data.CrashEvent
import com.sitech.paas.tracer.service.CrashService
import com.sitech.paas.tracer.service.DBCommonService
import com.sitech.paas.tracer.timer.CycleUpload
import com.sitech.paas.tracer.utils.GSON
import kotlinx.android.synthetic.main.activity_crash.*
import java.lang.Exception
import java.lang.NullPointerException

class CrashActivity : AppCompatActivity() {
    private lateinit var eventList: MutableList<CrashEvent>
    var data: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash)
        recycle.layoutManager = LinearLayoutManager(this)
        recycle.adapter = CommonAdapter(this, data)
        btn_click_crash.setOnClickListener {
            var i = 1 / 0
        }
        btn_clear_crash.setOnClickListener {
            DBCommonService.deleteCrashTable()
            data.clear()
            getData()
        }
        btn_nullpoint.setOnClickListener {
            if (true) {
                throw NullPointerException()
            }

        }
        btn_delete_ainfo.setOnClickListener {
            for (e in eventList) {
                CycleUpload.deleteEvent(e)
            }
        }
        getData()
    }

    private fun getData() {
//        eventList = CrashService.getCrashEventList(false) as MutableList<CrashEvent>
//        eventList.forEach {
//            data.add(it.toString())
//        }
//        e(data)
//        recycle.adapter?.notifyDataSetChanged()
    }
}
