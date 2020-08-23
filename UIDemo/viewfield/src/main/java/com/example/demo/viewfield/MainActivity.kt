package com.example.demo.viewfield

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_red.setOnClickListener {
            tv_red.x = tv_red.x + 100
            tv_red.y = tv_red.y + 100
        }
        tv_green.setOnClickListener {
            tv_green.translationX = 100f
            tv_green.translationY = 100f
        }
        tv_print.setOnClickListener {
//            printInfo(tv_red, "redtagtag")
//            printInfo(tv_green, "greentagtag")
            printInfo(tv_blue, "bluetagtag")
        }
        tv_blue.setOnClickListener {
            tv_blue.offsetLeftAndRight(100)
            tv_blue.offsetTopAndBottom(100)
        }
        container.setOnClickListener {
            container.scrollX = -100
            container.scrollY = -100
        }

//        startActivity(Intent(this, ScrollXYActivity::class.java))
    }

    private fun printInfo(tv: TextView?, tag: String) {
        print(tag + ":")
        println("$tag+x:${tv?.x}")
        println("$tag+y:${tv?.y}")
        println("$tag+translationX:${tv?.translationX}")
        println("$tag+translationY:${tv?.translationX}")
        println("$tag+left:${tv?.left}")
        println("$tag+top:${tv?.top}")
        println("$tag+scrollX:${tv?.scrollX}")
        println("$tag+scrollY:${tv?.scrollY}")
        println("scrollX" + container.scrollX)
    }
}
