package com.mage.dexdemo;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.provider.SyncStateContract;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * author  :mayong
 * function:
 * date    :2019-10-11
 **/
public class App extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        File apkFile = new File(getApplicationInfo().sourceDir);
        File versionDir = getDir("yangkun", Context.MODE_PRIVATE);
        File appDir = new File(versionDir, "app");
        File dexDir = new File(appDir, "dexDir");

        //得到我们需要加载的Dex文件
        List<File> dexFiles = new ArrayList<>();
        //进行解密（最好做MD5文件校验）
        if (!dexDir.exists() || dexDir.list().length == 0) {

        } else {
            for (File file : dexDir.listFiles()) {
                dexFiles.add(file);
            }
        }

        String signValidString = getSignValidString();
        System.out.println("md5="+signValidString);
    }


    private Signature[] getRawSignature(Context context, String packageName) {
        if (packageName == null || packageName.length() == 0) {
            return null;
        }
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            if (info != null) {
                return info.signatures;
            }
            //errout("info is null, packageName = " + packageName);
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            //errout("NameNotFoundException");
            return null;
        }
    }

    /**
     * 获得md5
     * @return
     */
    private String getSignValidString()  {
        Signature[] rawSignature = getRawSignature(this, getPackageName());

        MessageDigest localMessageDigest = null;
        try {
            localMessageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        localMessageDigest.update(rawSignature[0].toByteArray());
        return toHexString(localMessageDigest.digest());
    }

    public String toHexString(byte[] paramArrayOfByte) {
        if (paramArrayOfByte == null) {
            return null;
        }
        StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
        for (int i = 0; ; i++) {
            if (i >= paramArrayOfByte.length) {
                localStringBuilder.deleteCharAt(localStringBuilder.length()-1);
                return localStringBuilder.toString().toUpperCase();
            }
            String str = Integer.toString(0xFF & paramArrayOfByte[i], 16);
            if (str.length() == 1) {
                str = "0" + str;
            }
            localStringBuilder.append(str);
            localStringBuilder.append(":");
        }
    }
}
