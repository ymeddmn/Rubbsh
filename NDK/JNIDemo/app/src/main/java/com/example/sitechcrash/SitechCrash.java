package com.example.sitechcrash;

public class SitechCrash {
    static {
        System.loadLibrary("SitechCrash");
    }
    public static native void install(String path);
    public static native void testcarsh();
}
