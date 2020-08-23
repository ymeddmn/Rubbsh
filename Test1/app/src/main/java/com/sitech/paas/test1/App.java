package com.sitech.paas.test1;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * author  :mayong
 * function:
 * date    :2020-05-26
 **/
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this, "5ecc7dee0cafb25f90000053", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.LEGACY_AUTO);
        MobclickAgent.setCatchUncaughtExceptions(true);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                MobclickAgent.onResume(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                MobclickAgent.onPause(activity); // 不能遗漏
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
