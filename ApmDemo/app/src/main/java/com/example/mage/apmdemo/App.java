package com.example.mage.apmdemo;

import android.app.Application;

import com.sitech.paas.tracer.SitechApm;

/**
 * author  :mayong
 * function:
 * date    :2019-10-23
 **/
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SitechApm.INSTANCE.initialize(this,"11");

    }
}
