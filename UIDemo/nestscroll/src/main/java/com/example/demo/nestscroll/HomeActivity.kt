package com.example.demo.nestscroll

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.demo.nestscroll.nest1.MainActivity
import com.example.demo.nestscroll.nest2.Nest2Activity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        nest1.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        nest2.setOnClickListener {
            startActivity(Intent(this, Nest2Activity::class.java))
        }
    }
}
