package com.example.demo.jni;

public class EncryptClass {
    public native String keyFromJNI();
    public native String cipherFromJNI();
    public native String algorithmFromJNI();
    public native String ivParamFromJNI();

    static {
        System.loadLibrary("soprotect");
    }

}
