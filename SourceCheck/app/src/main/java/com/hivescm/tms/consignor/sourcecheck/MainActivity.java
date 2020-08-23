package com.hivescm.tms.consignor.sourcecheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hhh);
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
        Request request = new Request.Builder()
                .url("http://www.baidu.com")//请求接口。如果需要传参拼接到接口后面。
                .build();//创建Request 对象
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Request.Builder b ;

    }
}
