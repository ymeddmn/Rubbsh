package com.mage.mipushtest;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * author  :mayong
 * function:
 * date    :2020-05-22
 **/
public class DemoApplication extends Application {

    public static final String APP_ID = "2882303761518403798";
    public static final String APP_KEY = "5391840387798";
    public static final String TAG = "your packagename";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化push推送服务
        MiPushClient.registerPush(this, APP_ID, APP_KEY);
        //打开Log
        LoggerInterface newLogger = new LoggerInterface() {

            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        Logger.setLogger(this, newLogger);
        MiPushClient.setAlias(this,"123",null);
    }


}