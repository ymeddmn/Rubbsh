package com.example.windowdemo3;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by manji
 * Date：2018/9/29 下午4:29
 * Desc：
 */
public class WindowUtil {

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private View mView;
    private TextView mCustomCancelView;

    private Point point = new Point();
    private Rect mDeleteRect = new Rect();
    private static final int mViewWidth = 100;
    private static final int mCancelViewSize = 200;
    private int statusBarHeight = 0;

    private WindowUtil() {

    }

    private static class SingletonInstance {
        @SuppressLint("StaticFieldLeak")
        private static final WindowUtil INSTANCE = new WindowUtil();
    }

    public static WindowUtil getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public void showPermissionWindow(Context context, OnPermissionListener onPermissionListener) {
        if (WindowUtil.checkFloatWindowPermission(context)) {
            showWindow(context);
        } else {
            onPermissionListener.showPermissionDialog();
        }
    }

    @SuppressLint("CheckResult")
    private void showWindow(Context context) {
        if (null == mWindowManager && null == mView && null == mCustomCancelView) {
            mWindowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
            mView = LayoutInflater.from(context).inflate(R.layout.article_window, null);
            mCustomCancelView = (TextView) LayoutInflater.from(context).inflate(R.layout.activity_test, null);
            mWindowManager.getDefaultDisplay().getSize(point);

            ImageView ivImage = mView.findViewById(R.id.aw_iv_image);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivImage.getLayoutParams();
            lp.width = WindowUtil.dip2px(mViewWidth - 20);
            lp.height = WindowUtil.dip2px(mViewWidth - 20);
            ivImage.setLayoutParams(lp);
//            String imageUrl = SPUtil.getStringDefault(WebViewActivity.ARTICLE_IMAGE_URL, "");
//            RequestOptions requestOptions = RequestOptions.circleCropTransform();
//            requestOptions.placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round);
//            Glide.with(context).load(imageUrl).apply(requestOptions).into(ivImage);

            initListener(context);

            mLayoutParams = new WindowManager.LayoutParams();
            WindowManager.LayoutParams mCancelViewLayoutParams = new WindowManager.LayoutParams();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                mCancelViewLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            } else {
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
                mCancelViewLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            }

            mCancelViewLayoutParams.format = PixelFormat.RGBA_8888;   //窗口透明
            mCancelViewLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;  //窗口位置
            mCancelViewLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            mCancelViewLayoutParams.height = WindowUtil.dip2px(45);
            mWindowManager.addView(mCustomCancelView, mCancelViewLayoutParams);

            mLayoutParams.format = PixelFormat.RGBA_8888;   //窗口透明
            mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;  //窗口位置
            mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            mLayoutParams.width = WindowUtil.dip2px(mViewWidth);
            mLayoutParams.height = WindowUtil.dip2px(mViewWidth);
            // 可以修改View的初始位置
//            mLayoutParams.x = 0;
//            mLayoutParams.y = 0;
            mWindowManager.addView(mView, mLayoutParams);
        }
    }

    public void dismissWindow() {
        if (mWindowManager != null && mView != null) {
            mWindowManager.removeViewImmediate(mView);
//            mCustomCancelView.startAnimate(false, data -> {
            mWindowManager.removeViewImmediate(mCustomCancelView);
            mWindowManager = null;
            mCustomCancelView = null;
            mView = null;
//            });
        }
    }

    private void initListener(final Context context) {


        final int mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        //设置触摸滑动事件
        mView.setOnTouchListener(new View.OnTouchListener() {
            int startX, startY;  //起始点
            boolean isPerformClick;  //是否点击
            int finalMoveX;  //最后通过动画将mView的X轴坐标移动到finalMoveX

            boolean isRemove;

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mDeleteRect.isEmpty()) {
                    mDeleteRect.set(point.x - mCustomCancelView.getMeasuredWidth(),
                            point.y - mCustomCancelView.getMeasuredHeight(),
                            point.x,
                            point.y);
                    mDeleteRect.left += mView.getWidth() / 2;
                    mDeleteRect.top += mView.getHeight() / 2;
                }
                Log.d("click", "onTouch: " + event.getAction());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        isPerformClick = true;
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //判断是CLICK还是MOVE
                        //只要移动过，就认为不是点击
                        if (Math.abs(startX - event.getX()) >= mTouchSlop || Math.abs(startY - event.getY()) >= mTouchSlop) {
                            isPerformClick = false;
                        }

                        mLayoutParams.x = (int) (event.getRawX() - startX);
                        //这里修复了刚开始移动的时候，悬浮窗的y坐标是不正确的，要减去状态栏的高度，可以将这个去掉运行体验一下
                        mLayoutParams.y = (int) (event.getRawY() - startY - statusBarHeight);
                        Log.e("TAG", "x---->" + mLayoutParams.x);
                        Log.e("TAG", "y---->" + mLayoutParams.y);
                        updateViewLayout();   //更新mView 的位置

//                        mCustomCancelView.startAnimate(true);
//                        mCustomCancelView.isInSide(isRemove(mLayoutParams.x + (mView.getMeasuredWidth() >> 1),
//                                mLayoutParams.y + ((mView.getMeasuredHeight() >> 1))));
                        return true;
                    case MotionEvent.ACTION_UP:
                        if (isPerformClick) {
                            mView.performClick();
                        }
                        isRemove = isRemove(mLayoutParams.x + (mView.getMeasuredWidth() >> 1),
                                mLayoutParams.y + ((mView.getMeasuredHeight() >> 1)));

                        //判断mView是在Window中的位置，以中间为界
                        if (mLayoutParams.x + mView.getMeasuredWidth() / 2 >= mWindowManager.getDefaultDisplay().getWidth() / 2) {
                            finalMoveX = mWindowManager.getDefaultDisplay().getWidth() - mView.getMeasuredWidth();
                        } else {
                            finalMoveX = 0;
                        }
                        if (isRemove) {
//                            SPUtil.setIntDefault(WebViewActivity.ARTICLE_ID, -1);
//                            SPUtil.setStringDefault(WebViewActivity.ARTICLE_JUMP_URL, "");
//                            SPUtil.setStringDefault(WebViewActivity.ARTICLE_IMAGE_URL, "");
                            dismissWindow();
                        } else {
//                            mCustomCancelView.startAnimate(false);
                            stickToSide();
                        }
                        return !isPerformClick;
                }
                return false;
            }

            private void stickToSide() {
                ValueAnimator animator = ValueAnimator.ofInt(mLayoutParams.x, finalMoveX).setDuration(Math.abs(mLayoutParams.x - finalMoveX));
                animator.setInterpolator(new BounceInterpolator());
                animator.addUpdateListener(animation -> {
                    mLayoutParams.x = (int) animation.getAnimatedValue();
                    updateViewLayout();
                });
                animator.start();
            }
        });
    }

    private boolean isRemove(int centerX, int centrY) {
        return mDeleteRect.contains(centerX, centrY);
    }

    private void updateViewLayout() {
        if (null != mView && null != mLayoutParams) {
            mWindowManager.updateViewLayout(mView, mLayoutParams);
        }
    }

    public void hideWindow() {
        if (mView != null) {
            mView.setVisibility(View.GONE);
        }
        if (mCustomCancelView != null) {
            mCustomCancelView.setVisibility(View.GONE);
        }
    }

    public void visibleWindow() {
        if (mView != null) {
            mView.setVisibility(View.VISIBLE);
        }
        if (mCustomCancelView != null) {
            mCustomCancelView.setVisibility(View.VISIBLE);
        }
    }

    interface OnPermissionListener {
        void showPermissionDialog();
    }
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(outMetrics);
        }
        return outMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(outMetrics);
        }
        return outMetrics.heightPixels;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelOffset(resourceId);
        }
        return result;
    }

    public static int getRealHeight(Context context) {
        return getScreenHeight(context) - getStatusBarHeight(context);
    }

    public static int dip2px(float dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, App.getInstance().getResources().getDisplayMetrics());
    }

    public static final int REQUEST_PERMISSION_CODE = 0x0110;

    public static WindowUtil.OnSuspensionPermissionListener mOnSuspensionPermissionListener;

    public static boolean checkFloatWindowPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(context);
        }
        return true;
    }

    public static void applyPermission(Context context, WindowUtil.OnSuspensionPermissionListener onSuspensionPermissionListener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            ((Activity) context).startActivityForResult(intent, REQUEST_PERMISSION_CODE);
            mOnSuspensionPermissionListener = onSuspensionPermissionListener;
        }
    }

    /**
     * 获取 emui 版本号
     *
     * @return
     */
    private static double getEmuiVersion() {
        try {
            String emuiVersion = getSystemProperty("ro.build.version.emui");
            String version = emuiVersion.substring(emuiVersion.indexOf("_") + 1);
            return Double.parseDouble(version);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 4.0;
    }

    private static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            Log.e("TAG", "Unable to read sysprop " + propName, ex);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Log.e("TAG", "Exception while closing InputStream", e);
                }
            }
        }
        return line;
    }

    /**
     * 去华为权限申请页面
     */
    private static void applyHuaWeiPermission(Context context) {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");//悬浮窗管理页面
            intent.setComponent(comp);
            if (getEmuiVersion() == 3.1) {
                //emui 3.1 的适配
                ((Activity) context).startActivityForResult(intent, REQUEST_PERMISSION_CODE);
            } else {
                //emui 3.0 的适配
                comp = new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity");//悬浮窗管理页面
                intent.setComponent(comp);
                ((Activity) context).startActivityForResult(intent, REQUEST_PERMISSION_CODE);
            }
        } catch (SecurityException e) {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager",
                    "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理，跳转到本app的权限管理页面,这个需要华为接口权限，未解决
            intent.setComponent(comp);
            ((Activity) context).startActivityForResult(intent, REQUEST_PERMISSION_CODE);
            Log.e("TAG", Log.getStackTraceString(e));
        } catch (ActivityNotFoundException e) {
            /**
             * 手机管家版本较低 HUAWEI SC-UL10
             */
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.Android.settings", "com.android.settings.permission.TabItem");//权限管理页面 android4.4
            intent.setComponent(comp);
            ((Activity) context).startActivityForResult(intent, REQUEST_PERMISSION_CODE);
            e.printStackTrace();
            Log.e("TAG", Log.getStackTraceString(e));
        } catch (Exception e) {
            //抛出异常时提示信息
            Toast.makeText(context, "进入设置页面失败，请手动设置", Toast.LENGTH_LONG).show();
            Log.e("TAG", Log.getStackTraceString(e));
        }
    }

    public static boolean checkIsHuaWeiRom() {
        return Build.MANUFACTURER.contains("HUAWEI");
    }

    public static void onActivityResult(Context context, int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (null != mOnSuspensionPermissionListener) {
                    mOnSuspensionPermissionListener.onPermissionGranted();
                }
            }
        }
    }
    private static SharedPreferences getSharedPreferences(String name) {
        return App.getInstance().getSharedPreferences(name, Context.MODE_PRIVATE);
    }
    public interface OnSuspensionPermissionListener {

        /**
         * 当权限请求完毕后的回调
         */
        void onPermissionGranted();
    }
}