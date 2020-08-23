package com.example.sitechapm.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sitech.paas.siplugindemo.R
import com.sitech.paas.tracer.utils.LocationController
import com.sitech.paas.tracer.utils.OSUtils
import kotlinx.android.synthetic.main.activity_device_info.*

class DeviceInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_info)
//        tv_deviceinfo.setText(OSUtils.getOsInfo(this).toJson())
//        tv_appinfo.setText(OSUtils.getAppInfo(this).toJson())
//        btn_location.setOnClickListener {
//            OSUtils.getLocation(this, object : LocationController.OnLocationGaintListener {
//                override fun onChange(j: Double, w: Double) {
//                    tv_jwd.setText("经度：" + j + "   " + "纬度:" + w)
//                }
//            })
//        }
//        OSUtils.requestPermission(this)
    }
}
