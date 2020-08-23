package com.sitech.paas.nixiangdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        type = 4;
        View btnHaha = findViewById(R.id.btn_haha);
        btnHaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    haha1();
                    System.out.println("ai1");
                } else if (type == 2) {
                    haha2();
                    System.out.println("ai2");
                } else {
                    haha3();
                    System.out.println("ai3");
                }
            }
        });
    }

    private void haha3() {
        System.out.println("哈哈3");
    }

    private void haha2() {
        System.out.println("haha2");
    }

    private void haha1() {
        System.out.println("haha1");
    }
}
