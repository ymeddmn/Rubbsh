package com.mage.permissionapplysettinggdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

/**
 * author  :mayong
 * function:
 * date    :2020-06-04
 **/
public class PermissionSettingPage {

    /**
     * toSettingPermission方法跳转到权限页面之前的dialog
     *
     * @param context
     */
    public static void toSettingPerDialog(final Context context, String message) {
        new AlertDialog.Builder(context).setTitle("提醒").setMessage(message).setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PermissionSettingPage.toSettingPermission(context);
                dialog.dismiss();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * 跳转到权限设置页面
     *
     * @param context
     */
    public static void toSettingPermission(final Context context) {
        Phone.checkBrand(new Phone.PhoneBrandImpl() {
            @Override
            public void isEmui() {
                super.isEmui();
                gotoHuaweiPermission(context);
            }

            @Override
            public void isMiui() {
                super.isMiui();
                gotoMiuiPermission(context);
            }

            @Override
            public void isVivo() {
                super.isVivo();
                gotoVivoPermission(context);
            }

            @Override
            public void isOppo() {
                super.isOppo();
                gotoOppoPermission(context);
            }

            @Override
            public void isNotHMOV() {
                super.isNotHMOV();
                getAppDetailSettingIntent(context);
            }
        });
    }

    /**
     * 调到vivo权限申请页面
     *
     * @param context
     */
    public static void gotoVivoPermission(Context context) {
        Intent localIntent;
        if (((Build.MODEL.contains("Y85")) && (!Build.MODEL.contains("Y85A"))) || (Build.MODEL.contains("vivo Y53L"))) {
            localIntent = new Intent();
            localIntent.setClassName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.PurviewTabActivity");
            localIntent.putExtra("packagename", context.getPackageName());
            localIntent.putExtra("tabId", "1");
            context.startActivity(localIntent);
        } else {
            localIntent = new Intent();
            localIntent.setClassName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity");
            localIntent.setAction("secure.intent.action.softPermissionDetail");
            localIntent.putExtra("packagename", context.getPackageName());
            context.startActivity(localIntent);
        }
    }

    /**
     * oppo手机权限页面
     *
     * @param context
     */
    public static void gotoOppoPermission(Context context) {

        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", context.getPackageName());
        ComponentName comp = new ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity");
        intent.setComponent(comp);
        context.startActivity(intent);
    }

    /**
     * 跳转到miui的权限管理页面
     */
    public static void gotoMiuiPermission(Context context) {
        try { // MIUI 8
            Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", context.getPackageName());
            context.startActivity(localIntent);
        } catch (Exception e) {
            try { // MIUI 5/6/7
                Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                localIntent.putExtra("extra_pkgname", context.getPackageName());
                context.startActivity(localIntent);
            } catch (Exception e1) { // 否则跳转到应用详情
                context.startActivity(getAppDetailSettingIntent(context));
            }
        }
    }

    /**
     * 跳转到魅族的权限管理系统
     */
    public static void gotoMeizuPermission(Context context) {
        try {
            Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent(context));
        }
    }

    /**
     * 华为的权限管理页面
     */
    public static void gotoHuaweiPermission(Context context) {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent(context));
        }

    }

    /**
     * 获取应用详情页面intent（如果找不到要跳转的界面，也可以先把用户引导到系统设置页面）
     *
     * @return
     */
    public static Intent getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        return localIntent;
    }

    /**
     * 调到系统设置页面
     *
     * @param context
     */
    public static void toSystemSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        context.startActivity(intent);
    }
}
