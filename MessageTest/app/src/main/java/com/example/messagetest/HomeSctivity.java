package com.example.messagetest;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class HomeSctivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sctivity);
        findViewById(R.id.btn_sendmsg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSctivity.this, SendMsgActivity.class));
            }
        });

        findViewById(R.id.btn_turn_sms).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                FController.sendMsg(HomeSctivity.this, "10086", "101");
            }
        });
        findViewById(R.id.btn_to_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PermissionUtils().toRequestPermission(HomeSctivity.this);
            }
        });
        findViewById(R.id.btn_have_sendmsg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasPer = ContextCompat.checkSelfPermission(HomeSctivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
                Toast.makeText(HomeSctivity.this,hasPer?"有权限":"无权限",Toast.LENGTH_LONG).show();
            }
        });
    }
}

