package com.mage.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * author  :mayong
 * function:
 * date    :2020-05-02
 **/
public class HelperReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e("actionReceive", action);
        if (action.equals(Intent.ACTION_TIME_TICK)) {
            System.out.println("时间戳：" + System.currentTimeMillis());
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date(System.currentTimeMillis()));
            int minute = instance.get(Calendar.MINUTE);
            if (minute == 0 || minute == 30) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
                String format = simpleDateFormat.format(System.currentTimeMillis());
                RxBus.getDefault().post("现在是北京时间:"+format+",少壮不努力，老大徒伤悲");
            }

        }
    }
}
