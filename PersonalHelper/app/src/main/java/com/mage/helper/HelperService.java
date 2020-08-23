package com.mage.helper;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.baidu.tts.client.SpeechSynthesizer;

import java.net.HttpURLConnection;
import java.util.Random;

import rx.functions.Action1;

/**
 * author  :mayong
 * function:
 * date    :2020-05-02
 **/
public class HelperService extends Service {
    private final Register register;
    private final Player mPlayer;
    private final Random random;
    private SpeechSynthesizer speechSynthesizer;
    private Handler handler = new Handler();
    public String [] musics = {
            "https://m701.music.126.net/20200503074851/c4c3a8d357b3f6a90fee9882d0445339/jdyyaac/530f/060f/565a/de73e0d7731ebf400d76a05e29cf8cc7.m4a",
    "https://m10.music.126.net/20200503075156/ed0af84ba1c0c7e3e111f659bead4c49/yyaac/5652/065e/5353/a87af1200714d29ca840d9b664e33bd8.m4a",
    "https://m10.music.126.net/20200503075340/d9ef8503d5711754d7b7a26dcf744476/yyaac/obj/wonDkMOGw6XDiTHCmMOi/2167524169/0a69/3cda/a702/f48698f6704e7df8d9e11993a50bb6de.m4a",
    "https://m801.music.126.net/20200503075641/8297b503170f6e5a1bebc1ac7829ea69/jdyyaac/5609/0453/5209/acd223661139b256f5a3ec28a8f1865e.m4a",
    "https://m801.music.126.net/20200503075858/63cc5e2b140bc40e9df5f5390226a639/jdyyaac/050b/5552/045e/2a28346f00b8487b6756347429bf30a7.m4a",
    "https://m701.music.126.net/20200503080003/ba34901945d968236ac87701070cf009/jdyyaac/555f/0358/5659/b56181f41542d09f23c3a36658241e55.m4a",
    "https://m801.music.126.net/20200503080115/b25c62c30349ad8fcf63014a157526d4/jdyyaac/040f/025c/550f/3e0afa81b856daf9583ebe0de5123295.m4a",
//    "https://m701.music.126.net/20200503080151/108b589bb6ec19597aa6285fb20c0df8/jdyyaac/070c/040e/5109/0fbfef823b3d7432a029fc92c60d7d2b.m4a",
    "https://m801.music.126.net/20200503080246/a13199d24453b1a790efbbc759578794/jdyyaac/545d/565e/560c/c7c93581fea4c7367b43afc623362f5d.m4a",
    "https://m701.music.126.net/20200503080340/15d2385d48b5121d16417633e8ea527e/jdyyaac/5509/5653/560e/bccf55d313a9efbd2442ad0fd940c281.m4a"


    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public HelperService() {
        register = new Register();
        random = new Random();
        RxBus.getDefault().toObservable(String.class).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                speechSynthesizer.speak(s);
            }
        });
         mPlayer = new Player();
    }

    private void getRequest() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        register.register(this);
        speechSynthesizer = new Controller().initTTs(this);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (speechSynthesizer != null) {
            speechSynthesizer.stop();
            speechSynthesizer.release();
            speechSynthesizer = null;
        }
        super.onDestroy();
        register.unRegister(this);

    }
}
