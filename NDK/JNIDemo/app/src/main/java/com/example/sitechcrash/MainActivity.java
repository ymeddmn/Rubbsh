package com.example.sitechcrash;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SitechCrash.install(Environment.getExternalStorageDirectory().getPath());

    }

    public void ck(View view){
        SitechCrash.testcarsh();
    }
}
