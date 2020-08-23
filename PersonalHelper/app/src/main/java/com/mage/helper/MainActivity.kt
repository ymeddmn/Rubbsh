package com.mage.helper

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy
import com.baidu.tts.client.SpeechError
import com.baidu.tts.client.SpeechSynthesizer
import com.baidu.tts.client.SpeechSynthesizerListener
import com.mage.helper.IOfflineResourceConst.DEFAULT_SDK_TTS_MODE
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPermission()
        startService(Intent(this, HelperService::class.java))
        findViewById<View>(R.id.iv).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
//                RxBus.getDefault().post("哈哈")
            }
        })
    }
    private fun initPermission() {
        val permissions = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE
        )

        val toApplyList = ArrayList<String>()

        for (perm in permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(
                    this,
                    perm
                )
            ) {
                toApplyList.add(perm)
                //进入到这里代表没有权限.
            }
        }
        val tmpList = arrayOfNulls<String>(toApplyList.size)
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123)
        }


    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。

    }

}
