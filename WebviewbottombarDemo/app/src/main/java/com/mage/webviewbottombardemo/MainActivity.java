package com.mage.webviewbottombardemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bottom;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
         bottom = findViewById(R.id.bottom);
         webView = findViewById(R.id.webview);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom.setVisibility(View.GONE);
            }
        });
//        webView.getBackground().setAlpha(0);
        View container = findViewById(R.id.wvcontainer);

        container.setBackgroundColor(0);
        webView.setBackgroundColor(0);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
//        webView.loadUrl("https://blog.csdn.net/qq_33073089/article/details/52948775");
        webView.loadUrl("http://172.18.193.53:9019/show/test/tan.html");
    }
}
