package com.mage.helper;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * author  :mayong
 * function:
 * date    :2020-05-02
 **/
public class Register {
    private final HelperReciver reciver;

    public Register() {
         reciver = new HelperReciver();
    }

    public void register(Context context){
        Log.e("tag","注册广播");
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        filter.addAction(Intent.ACTION_TIME_TICK);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        context.registerReceiver(reciver, filter);
    }
    public void unRegister(Context context){
        Log.e("tag","注销广播");
        context.unregisterReceiver(reciver);
    }
}
