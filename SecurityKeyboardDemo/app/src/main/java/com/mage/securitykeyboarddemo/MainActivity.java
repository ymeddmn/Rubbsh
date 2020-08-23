package com.mage.securitykeyboarddemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView contentWebView = null;
    private EditText editText;
    private Activity mActivity;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mActivity = MainActivity.this;
        editText = (EditText) findViewById(R.id.edit_tv);
        editText.setInputType(InputType.TYPE_NULL);
        editText.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                new KeyboardUtil(mActivity, mActivity, editText).showKeyboard();
                return true;
            }
        });
        //Context mContext=this;
        //View view=LayoutInflater.from(this).inflate(R.layout.main, null);
        contentWebView = (WebView) findViewById(R.id.webview);
        contentWebView.requestDisallowInterceptTouchEvent(true);
        // 允许执行javascript
        contentWebView.getSettings().setJavaScriptEnabled(true);
        // 访问assetst文件中得html
        contentWebView.loadUrl("file:///android_asset/wst.html");
        // final JavaScriptInterface myJavaScriptInterface
        // = new JavaScriptInterface(this);
        // contentWebView.addJavascriptInterface(myJavaScriptInterface,
        // "AndroidFunction");
        contentWebView.addJavascriptInterface(this, "wst");
        contentWebView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        InputMethodManager imm = (InputMethodManager) contentWebView
                                .getContext().getSystemService(
                                        Context.INPUT_METHOD_SERVICE);
                        if (imm.isActive()) {
                            imm.hideSoftInputFromWindow(
                                    contentWebView.getApplicationWindowToken(),
                                    0);
                        }
                    }
                }, 500);

                return false;
            }
        });

    }

    // java调用js，并更新web页面中得内容
    OnClickListener btnClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            // java调用无参js
            contentWebView.loadUrl("javascript:javacalljs()");
            // java调用有参js
            contentWebView.loadUrl("javascript:javacalljswithargs("
                    + "'hello world'" + ")");
            contentWebView.loadUrl("javascript:javacalljswithargs("
                    + "'hello world'" + "," + "'zzz'" + ")");
        }
    };

    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        public void showToast(String webMessage) {
            final String msgeToast = webMessage;
            // myHandler.post(new Runnable() {
            // @Override
            // public void run() {
            // // This gets executed on the UI thread so it can safely modify
            // Views
            // myTextView.setText(msgeToast);
            // }
            // });

            Toast.makeText(mContext, webMessage + "89898", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // js调用java更新Android中代码
    @JavascriptInterface
    public void startFunction() {
        Toast.makeText(this, "js调用java代码,无参", Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                new KeyboardUtil(mActivity, mActivity,contentWebView).showKeyboard();
            }
        });
    }
    @JavascriptInterface
    public void startFunction(final String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                //msgView.setText(msgView.getText() + "\njs调用java代码2" + str);

            }
        });
    }
    @JavascriptInterface
    public void startFunction(final String str, final String str2) {
        Toast.makeText(this, str + "---" + str2, Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                //msgView.setText(msgView.getText() + "\njs调用java代码3" + str
                //		+ "----" + str2);
            }
        });
    }
    public void showToast(final String str) {
        Toast.makeText(this, str + "8989", Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                //	msgView.setText(msgView.getText() + "\njs调用java代码4" + str
                //			+ "----8989");

            }
        });
    }
}
