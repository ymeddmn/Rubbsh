package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BaseReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("haha")){
            Intent intent1=new Intent();
            context.sendBroadcast(intent1);
        }
    }
}
