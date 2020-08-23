package com.example.windowdemo1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

/**
 * 弹窗辅助类
 *
 * @ClassName WindowUtils
 */
public class WindowUtils {
    private static final String LOG_TAG = "WindowUtils";
    private static View mView = null;
    private static WindowManager mWindowManager = null;
    private static Context mContext = null;
    public static Boolean isShown = false;

    public static boolean checkFloatWindowPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(context);
        }
        return true;
    }

    public static void applyPermission(Context context, OnSuspensionPermissionListener onSuspensionPermissionListener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            ((Activity) context).startActivityForResult(intent, 4000);
        }
    }
    /**
     * 显示弹出框
     *
     * @param context
     */
    public static void showPopupWindow(final Context context) {
        if (isShown) {
            return;
        }
        isShown = true;
        // 获取应用的Context
        mContext = context.getApplicationContext();
        // 获取WindowManager
        mWindowManager = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        mView = setUpView(context);
        final LayoutParams params = new LayoutParams();
        // 类型
        params.type = LayoutParams.TYPE_APPLICATION_OVERLAY;
        // WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        // 设置flag
        int flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
                | LayoutParams.FLAG_NOT_FOCUSABLE;
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
        params.flags = flags;
        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSLUCENT;
        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
        // 不设置这个flag的话，home页的划屏会有问题
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        mWindowManager.addView(mView, params);
    }

    /**
     * 隐藏弹出框
     */
    public static void hidePopupWindow() {
        if (isShown && null != mView) {
            mWindowManager.removeView(mView);
            isShown = false;
        }
    }

    private static View setUpView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.popwindow,
                null);
        Button positiveBtn = (Button) view.findViewById(R.id.positiveBtn);
        positiveBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开安装包
                // 隐藏弹窗
                WindowUtils.hidePopupWindow();
            }
        });
//        Button negativeBtn = (Button) view.findViewById(R.id.negativeBtn);
//        negativeBtn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WindowUtils.hidePopupWindow();
//            }
//        });
//        // 点击窗口外部区域可消除
//        // 这点的实现主要将悬浮窗设置为全屏大小，外层有个透明背景，中间一部分视为内容区域
//        // 所以点击内容区域外部视为点击悬浮窗外部
//        final View popupWindowView = view.findViewById(R.id.popup_window);// 非透明的内容区域
//        view.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int x = (int) event.getX();
//                int y = (int) event.getY();
//                Rect rect = new Rect();
//                popupWindowView.getGlobalVisibleRect(rect);
//                if (!rect.contains(x, y)) {
//                    WindowUtils.hidePopupWindow();
//                }
//
//                return false;
//            }
//        });
        return view;

    }
}