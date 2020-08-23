package com.example.webviewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var jj = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       w()

        x()
        jj="ee"
    }

    private fun x() {

    }

    private fun w() {


        webview.webChromeClient = WebChromeClient()

        WebChromeClient()

        var s:String="sdf"
        var m:MainActivity


        webview.loadUrl("https://blog.csdn.net/byc233518/article/details/52437498")


        println(javaClass.name)

    }
}
