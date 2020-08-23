package com.mage.helper;

import android.app.Application;

import com.baidu.tts.client.SpeechSynthesizer;

/**
 * author  :mayong
 * function:
 * date    :2020-05-02
 **/
public class App extends Application {
    public static SpeechSynthesizer speechSynthesizer;

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
