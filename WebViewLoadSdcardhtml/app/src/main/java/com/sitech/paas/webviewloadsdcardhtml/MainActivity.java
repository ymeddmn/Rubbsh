package com.sitech.paas.webviewloadsdcardhtml;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WebView webview = findViewById(R.id.webview);
        webview.getSettings().setAllowFileAccess(true);
        webview.setWebChromeClient(new WebChromeClient());
//        webview.loadDataWithBaseURL("file:///storage/emulated/0/test.html","","text/html","utf-8","");
//        webview.loadUrl("file:///storage/emulated/0/test.html");
        webview.loadUrl("https://blog.csdn.net/turing1992/article/details/68061608");
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    System.out.println("shouldOverrideUrlLoading:"+request.getUrl().toString());
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    System.out.println("shouldInterceptRequest:"+request.getUrl().toString());
                }
                return super.shouldInterceptRequest(view, request);
            }


            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                System.out.println("onLoadResource:"+url);
            }
        });
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.reload();
            }
        });
    }
}
