package com.example.windowdemo1;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.WindowManager;


import java.lang.reflect.Method;

public class Permission1 {
    static boolean hasPermission(Context context) {
        return Build.VERSION.SDK_INT >= 23 ? Settings.canDrawOverlays(context) : hasPermissionBelowMarshmallow(context);
    }

    static boolean hasPermissionOnActivityResult(Context context) {
        if (Build.VERSION.SDK_INT == 26) {
            return hasPermissionForO(context);
        } else {
            return Build.VERSION.SDK_INT >= 23 ? Settings.canDrawOverlays(context) : hasPermissionBelowMarshmallow(context);
        }
    }

    static boolean hasPermissionBelowMarshmallow(Context context) {
        try {
            AppOpsManager manager = (AppOpsManager)context.getSystemService("appops");
            Method dispatchMethod = AppOpsManager.class.getMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class);
            return 0 == (Integer)dispatchMethod.invoke(manager, 24, Binder.getCallingUid(), context.getApplicationContext().getPackageName());
        } catch (Exception var3) {
            return false;
        }
    }

    @RequiresApi(
            api = 23
    )
    private static boolean hasPermissionForO(Context context) {
        try {
            WindowManager mgr = (WindowManager)context.getSystemService("window");
            if (mgr == null) {
                return false;
            } else {
                View viewToAdd = new View(context);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams(0, 0, Build.VERSION.SDK_INT >= 26 ? 2038 : 2003, 24, -2);
                viewToAdd.setLayoutParams(params);
                mgr.addView(viewToAdd, params);
                mgr.removeView(viewToAdd);
                return true;
            }
        } catch (Exception var4) {
            return false;
        }
    }
}
