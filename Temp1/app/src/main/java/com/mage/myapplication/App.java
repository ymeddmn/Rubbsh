package com.mage.myapplication;

import android.app.Application;

/**
 * author  :mayong
 * function:
 * date    :2020-07-07
 **/
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Thread.UncaughtExceptionHandler mDefathander = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
    }
}
