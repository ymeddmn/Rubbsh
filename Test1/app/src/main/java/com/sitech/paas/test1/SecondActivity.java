package com.sitech.paas.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class SecondActivity extends AppCompatActivity {

    private TextView tv;
    private int a[] = new int[2097150];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        tv = findViewById(R.id.tv);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        tv.setText("进入app时间："+simpleDateFormat.format(System.currentTimeMillis()));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
            }
        },1000);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("te调用保存方法","onRestoreInstanceState");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            outState.putString("info","app于"+simpleDateFormat.format(System.currentTimeMillis())+"被销毁");
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String info = savedInstanceState.getString("info");
        Log.e("te调用恢复方法","onRestoreInstanceState");
        if(!TextUtils.isEmpty(info)){
            tv.setText(info);
        }
    }

}
