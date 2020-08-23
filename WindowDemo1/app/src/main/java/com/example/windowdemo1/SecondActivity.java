package com.example.windowdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yhao.floatwindow.FloatWindow;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatWindow.get("haha").show();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        FloatWindow.get("haha").show();
    }
}
