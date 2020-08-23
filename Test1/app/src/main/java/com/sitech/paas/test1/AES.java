package com.sitech.paas.test1;

/**
 * author  :mayong
 * function:
 * date    :2020-04-22
 **/

import android.util.Base64;

import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AES {

    // static String e = "9238513401340235";
    static byte[] ivByte = {85, 60, 12, 116, 99, (byte) (-67 + 256), (byte) (-83 + 256), 19, (byte) (-118 + 256), (byte) (-73 + 256), (byte) (-24 + 256), (byte) (-8 + 256), 82, (byte) (-24 + 256), (byte) (-56 + 256), (byte) (-14 + 256)};

    // 加密
    public static String Encrypt(String src, String key) throws Exception {
        if (key == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (key.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"0102030405060708
        IvParameterSpec ivps = new IvParameterSpec(ivByte);// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivps);

        byte[] encrypted = cipher.doFinal(src.getBytes("UTF-8"));
        return         Base64.encodeToString(encrypted,Base64.DEFAULT);

        // return Base64.encodeBytes(encrypted);
        // 此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String Decrypt(String src, String key) throws Exception {
        // 判断Key是否正确
        if (key == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (key.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = key.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivps = new IvParameterSpec(ivByte);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivps);
        // byte[] encrypted1 = Base64.decode(src);// 先用base64解密
//        byte[] encrypted1 = Base64.decodeBase64(src.getBytes("utf-8"));
        byte[] encrypted1 = Base64.decode(src.getBytes("utf-8"), Base64.DEFAULT);
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original);
        return originalString;

    }

    public static void main(String[] args) throws Exception {
        String key = "aspire_share_aes";
        String token = "18896725498:20170214145959:122323";
        System.out.println("加密前：" + token);
        // app token加密
        long start = System.currentTimeMillis();
        String enString = AES.Encrypt(token, key);
        System.out.println("密文是：" + enString);

        long useTime = System.currentTimeMillis() - start;
        System.out.println("加密耗时：" + useTime + "毫秒");
        System.out.println("密文URLEncode后是：" + URLEncoder.encode(enString, "UTF-8"));
        // 解密
        start = System.currentTimeMillis();
        String DeString = AES.Decrypt(enString, key);
        System.out.println("解密后的明文是：" + DeString);
        useTime = System.currentTimeMillis() - start;
        System.out.println("解密耗时：" + useTime + "毫秒");

        String cc = "1100000011";
        System.out.println("加密前：" + cc);
        // pc 渠道编号cc加密
        start = System.currentTimeMillis();
        enString = AES.Encrypt(cc, key);
        System.out.println("密文是：" + enString);

        useTime = System.currentTimeMillis() - start;
        System.out.println("加密耗时：" + useTime + "毫秒");
        System.out.println("密文URLEncode后是：" + URLEncoder.encode(enString, "UTF-8"));
        // 解密
        start = System.currentTimeMillis();
        DeString = AES.Decrypt(enString, key);
        System.out.println("解密后的明文是：" + DeString);
        useTime = System.currentTimeMillis() - start;
        System.out.println("解密耗时：" + useTime + "毫秒");
    }
}