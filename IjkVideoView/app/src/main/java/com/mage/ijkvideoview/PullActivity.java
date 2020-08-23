package com.mage.ijkvideoview;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

public class PullActivity extends AppCompatActivity {

    private IjkVideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);



        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        videoView = (IjkVideoView) findViewById(R.id.player);
        AndroidMediaController controller = new AndroidMediaController(this, false);
        videoView.setMediaController(controller);
        String url = "http://movie.vods1.cnlive.com/3/vod/2017/0607/3_5d21bed962f44c8eac068942745187ef/ff8080815bf6b453015c83457e311a95_1500.m3u8";
        videoView.setVideoURI(Uri.parse(url));
        System.out.println("空");
        videoView.start();
    }
}
