package com.mage.submarinegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val background = findViewById<BackgroundView>(R.id.background)
        findViewById<Button>(R.id.btn).setOnClickListener({
            background.start()
        })
    }
}
