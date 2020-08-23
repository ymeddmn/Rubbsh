package com.hivescm.tms.consignor.demo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mayong on 2018/7/9.
 */

public class BaseActivity extends AppCompatActivity {

    private WebView webview;
    private WebView webView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        webview = (WebView) findViewById(R.id.webview);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUseWideViewPort(false);
//        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        settings.setPluginState(WebSettings.PluginState.ON);
//        settings.setPluginsEnabled(true);//可以使用插件
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webview.setWebChromeClient(new WebChromeClient());
        String url = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(url)) {
            webview.loadUrl(url);
        }
        webview.addJavascriptInterface(new JSInterface(this, webView), "obj");
        webview.loadUrl("http://192.168.174.83:8888/#/jsbridge");
        Map<String, Object> map = new HashMap<>();
        map.put("key", "哈哈哈哈");
        JSONObject jo = new JSONObject(map);
        Object jsonStr = jo.toString();

        String methodName = "javascript:test1()";

        findViewById(R.id.btn).setOnClickListener(v -> {
//            webview.evaluateJavascript(methodName, new ValueCallback<String>() {
//                @Override
//                public void onReceiveValue(String value) {
//                    System.out.println("被点击1" + value);
//                }
//            });
            String methodName2 = "javascript:test2('"+ jsonStr+"')";
            webview.evaluateJavascript(methodName2, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    System.out.println("被点击2" + value);
                }
            });
        });
    }

    private class JSInterface {
        private WebView webView;
        private Context context;

        public JSInterface(Context context, WebView webView) {
            this.context = context;
            this.webView = webView;
        }

        /**
         * 是否显示分享按钮
         */
        @JavascriptInterface
        public void showToast() {
            Toast.makeText(context, "被点击", Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public String jsonStr(String json) {
            System.out.println("被点击");
            return json;
        }

        @JavascriptInterface
        public String demo1(String json) {
            Toast.makeText(context, "demo1", Toast.LENGTH_LONG).show();
            Map<String, Object> map = new HashMap<>();
            map.put("key", "demo1");
            JSONObject jo = new JSONObject(map);
            return jo.toString();
        }

        @JavascriptInterface
        public String demo2(String json) {
            Toast.makeText(context, "demo2", Toast.LENGTH_LONG).show();
            Map<String, Object> map = new HashMap<>();
            map.put("key", "demo2");
            JSONObject jo = new JSONObject(map);
            return jo.toString();
        }

        @JavascriptInterface
        public String demo3(String json) {
            Toast.makeText(context, "demo3", Toast.LENGTH_LONG).show();
            Map<String, Object> map = new HashMap<>();
            map.put("key", "demo3");
            JSONObject jo = new JSONObject(map);
            return jo.toString();
        }

        @JavascriptInterface
        public String demo4(String json) {
            Toast.makeText(context, "demo4", Toast.LENGTH_LONG).show();
            Map<String, Object> map = new HashMap<>();
            map.put("key", "demo4");
            JSONObject jo = new JSONObject(map);
            return jo.toString();
        }

    }
}
