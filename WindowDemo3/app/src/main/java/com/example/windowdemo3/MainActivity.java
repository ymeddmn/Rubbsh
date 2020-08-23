package com.example.windowdemo3;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(v->{
            WindowUtil.getInstance().showPermissionWindow(this, new WindowUtil.OnPermissionListener() {
                @Override
                public void showPermissionDialog() {
                    System.out.println("获得了权限");
                    Toast.makeText(MainActivity.this,"获得了权限",Toast.LENGTH_LONG).show();
                    WindowUtil.applyPermission(MainActivity.this, () -> {
                        new Handler().postDelayed(() -> {
                            if (!WindowUtil.checkFloatWindowPermission(MainActivity.this)) {
                                // 授权失败
//                                showDialog();
                                System.out.println("授权失败");
                                Toast.makeText(MainActivity.this,"授权失败",Toast.LENGTH_LONG).show();
                            } else {
                                System.out.println("授权成功");
                                Toast.makeText(MainActivity.this,"授权成功",Toast.LENGTH_LONG).show();
                                WindowUtil.getInstance().showPermissionWindow(MainActivity.this,null);
                            }
                        }, 500);
                    });
                }
            });
        });
        WindowUtil.getInstance().showPermissionWindow(this, new WindowUtil.OnPermissionListener() {
            @Override
            public void showPermissionDialog() {
                System.out.println("获得了权限");
                Toast.makeText(MainActivity.this,"获得了权限",Toast.LENGTH_LONG).show();
                WindowUtil.applyPermission(MainActivity.this, () -> {
                    new Handler().postDelayed(() -> {
                        if (!WindowUtil.checkFloatWindowPermission(MainActivity.this)) {
                            // 授权失败
//                                showDialog();
                            System.out.println("授权失败");
                            Toast.makeText(MainActivity.this,"授权失败",Toast.LENGTH_LONG).show();
                        } else {
                            System.out.println("授权成功");
                            Toast.makeText(MainActivity.this,"授权成功",Toast.LENGTH_LONG).show();
                            WindowUtil.getInstance().showPermissionWindow(MainActivity.this,null);
                        }
                    }, 500);
                });
            }
        });
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        startActivity(new Intent(MainActivity.this,SecondActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        WindowUtil.onActivityResult(this, requestCode, resultCode, data);
    }
}
