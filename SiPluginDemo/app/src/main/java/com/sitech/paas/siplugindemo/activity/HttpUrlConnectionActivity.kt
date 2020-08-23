package com.example.sitechapm.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sitech.paas.siplugindemo.R
import kotlinx.android.synthetic.main.activity_http_url_connection.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

class HttpUrlConnectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_url_connection)
        btn_httpurl.setOnClickListener {
            Thread(Runnable {
                get()
            }).start()
        }
    }

    fun get(): String {
        var message = ""
        try {
            val url = URL("https://www.baidu.com/")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 5 * 1000
            connection.connect()
            val inputStream = connection.inputStream
            if (connection.responseCode == 200) {
                val data = ByteArray(1024)
                val sb = StringBuffer()
                var length = 0
                while (length != -1) {
                    val s = String(data, Charset.forName("utf-8"))
                    sb.append(s)
                    length = inputStream.read(data)
                }
                message = sb.toString()
            }
            inputStream.close()
            connection.disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return message
    }
}
