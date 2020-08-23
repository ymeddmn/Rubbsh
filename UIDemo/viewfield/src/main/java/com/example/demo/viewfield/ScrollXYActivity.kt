package com.example.demo.viewfield

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import kotlinx.android.synthetic.main.activity_scroll_xy.*

class ScrollXYActivity : AppCompatActivity() {
    var width = 0
    var flag = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_xy)
        container.setOnClickListener {
            var scrollX = container.scrollX
            if (flag > 0) {
                if (scrollX >= width * 2) {
                    flag = -1
                }
            } else {
                if (scrollX <= 0) {
                    flag = 1
                }
            }
            container.scrollX = scrollX + width * flag
        }
        width = resources.displayMetrics.widthPixels
    }

}
