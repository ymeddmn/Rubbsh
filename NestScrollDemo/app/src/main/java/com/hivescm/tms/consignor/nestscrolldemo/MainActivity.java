package com.hivescm.tms.consignor.nestscrolldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.hivescm.tms.consignor.nestscrolldemo.ui.coordinate.CoordinateManagerActivity;
import com.hivescm.tms.consignor.nestscrolldemo.ui.scroll.ScrollActivity;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycle;
    Button btnCoor, btnScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        btnCoor = findViewById(R.id.btn_coor);
        btnScroll = findViewById(R.id.btn_scroll);
    }

    private void initListener() {
        btnCoor.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CoordinateManagerActivity.class));
        });

        btnScroll.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ScrollActivity.class));
        });
    }


}
