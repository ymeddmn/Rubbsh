package com.example.windowdemo3;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class AppLifeCycle implements Application.ActivityLifecycleCallbacks {
    private static int started = 0;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        started++;
        if (started == 1) {
            Log.e("TAG", "应用在前台了！！！");
//            if (SPUtil.getIntDefault(WebViewActivity.ARTICLE_ID, -1) > 0) {
//                activity?.startService(Intent(activity, WindowShowService::class.java))
//            }
            WindowUtil.getInstance().showPermissionWindow(activity, new WindowUtil.OnPermissionListener() {
                @Override
                public void showPermissionDialog() {
                    WindowUtil.applyPermission(activity, () -> {
                        new Handler().postDelayed(() -> {
                            if (!WindowUtil.checkFloatWindowPermission(activity)) {
                                // 授权失败
//                                showDialog();
                            } else {
                                WindowUtil.getInstance().showPermissionWindow(activity,null);
                            }
                        }, 300);
                    });
                }
            });
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        started--;
        if (started == 0) {
            WindowUtil.getInstance().dismissWindow();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
