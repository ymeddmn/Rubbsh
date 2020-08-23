package com.sitech.tv4.lib;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.sitech.tv4.AppManager;

/**
 * @author wangyongmei
 * @describe describe
 * @time 2019/5/14 下午2:42
 */
public class OSUtils {
    private Activity mActivity;
    private Context mContext;
    private WebView mWebview;
    public OSUtils(Activity activity, WebView mAcMobileWebGapClass) {
        this.mActivity = activity;
        this.mContext = activity;
        this.mWebview = mAcMobileWebGapClass;

    }
    // 退出应用,android手机返回键调用
    @JavascriptInterface
    public void quit() {
        //Log.i("WYM","quit");
        /*
        final Activity activity = (Activity) mContext;
        mActivity.moveTaskToBack(true);
        */
        AppManager.getAppManager().AppExit(mActivity);

    }
}
