package com.example.windowdemo1;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestOverlayPermission();
            }
        });
    }


    private static final int REQUEST_OVERLAY = 4444;

    private void requestOverlayPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(MainActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_OVERLAY);
            } else {
                showWindow();
            }
        }else {
            showWindow();
        }
    }

    private void showWindow() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_OVERLAY){
            if(resultCode==RESULT_OK){
                showWindow();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
