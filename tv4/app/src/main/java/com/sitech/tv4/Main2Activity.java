package com.sitech.tv4;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sitech.tv4.lib.OSUtils;

public class Main2Activity extends Activity {

    private WebView mWebView;
    private OSUtils osUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        setContentView(R.layout.activity_main2);
        mWebView = findViewById(R.id.web);



        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);


        //支持javascript
        settings.setJavaScriptEnabled(true);
         // 设置可以支持缩放
        settings.setSupportZoom(true);
        // 设置出现缩放工具
        settings.setBuiltInZoomControls(true);
         //扩大比例的缩放
        settings.setUseWideViewPort(true);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        //缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        osUtils = new OSUtils(this,mWebView);
        mWebView.addJavascriptInterface(osUtils, "OSUtils");

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //Log.d("WYM","加载开始");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //Log.d("WYM","加载结束");
            }

            // 链接跳转都会走这个方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Log.d("WYM","Url："+ url );
//                view.loadUrl(url);
//                return true;
                //Android 8.0以下版本的需要返回true 并且需要loadUrl()
                if (Build.VERSION.SDK_INT < 26) {
                    view.loadUrl(url);
                    return true;
                }
                return false;

            }

            @Override
            public void onReceivedError(final WebView view, int errorCode,
                                        String description, final String failingUrl) {
                Log.d("WYM",failingUrl );
            }
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });

//        mWebView.loadUrl("http://he.sx.chinamobile.com/h/index.html#/communication");//tv3
//        mWebView.loadUrl("http://www.baidu.com");



//        mWebView.loadUrl("file:///android_asset/html/IPTV/index.html");

//        mWebView.loadUrl("http://172.21.12.127:31002/npage/index.html");
        mWebView.loadUrl("http://localhost:3000/npage/index.html");


    }

   @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();//执行webview.goBack()
            return true;
        }
        if((keyCode == KeyEvent.KEYCODE_BACK) && !mWebView.canGoBack()){
            mWebView.loadUrl("javascript:goback();");
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


}
