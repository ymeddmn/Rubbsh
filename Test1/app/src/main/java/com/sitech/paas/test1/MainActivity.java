package com.sitech.paas.test1;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileFilter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    String enText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button en = findViewById(R.id.btn_en);
        Button de = findViewById(R.id.btn_de);
        Button btnMac = findViewById(R.id.btn_mac);
        en.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                System.out.println("唯一id"+getUniqueId(MainActivity.this));
                throw new NullPointerException("空指针异常");
            }
        });
        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnMac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getmac = MacAddressUtils.getmac();
                System.out.println("mac地址：" + getmac);
                System.out.println("ip地址：" + IpUtils.getIPAddress(MainActivity.this));
            }
        });
        startActivity(new Intent(this,FileIoActivit.class));

    }

    @RequiresPermission(value = Manifest.permission.READ_PHONE_STATE)
    @SuppressLint("MissingPermission")
    public static String getUniqueId(Context context){
        @SuppressLint("HardwareIds")
        // ANDROID_ID是设备第一次启动时产生和存储的64bit的一个数，当设备被wipe后该数重置。
                String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        @SuppressLint("HardwareIds")
        String id = null; // +硬件序列号
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            id = androidID + Build.getSerial();
        }else {
            id = androidID + Build.SERIAL;
        }
        try {
            return toMD5(id);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return id;
        }
    }
    public static String toMD5(String text) throws NoSuchAlgorithmException {
        //获取摘要器 MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //通过摘要器对字符串的二进制字节数组进行hash计算
        byte[] digest = messageDigest.digest(text.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            //循环每个字符 将计算结果转化为正整数;
            int digestInt = digest[i] & 0xff;
            //将10进制转化为较短的16进制
            String hexString = Integer.toHexString(digestInt);
            //转化结果如果是个位数会省略0,因此判断并补0
            if (hexString.length() < 2) {
                sb.append(0);
            }
            //将循环结果添加到缓冲区
            sb.append(hexString);
        }
        //返回整个结果
        return sb.toString().substring(8,24);
    }

}
