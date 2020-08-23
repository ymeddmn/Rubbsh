package com.mage.ijkvideoview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class Video1Activity extends AppCompatActivity implements SurfaceHolder.Callback{
    private VideoView vv_view;
    private SurfaceView sv_view;
    private IjkMediaPlayer ijkMediaPlayer;
    private SurfaceHolder holder;
//    String testURl="https://video-dev.github.io/streams/x36xhzz/x36xhzz.m3u8";
//    String testURl1="http://weblive.hebtv.com/live/hbgg_bq/index.m3u8";
//    String testURl2="https://video-dev.github.io/streams/x36xhzz/x36xhzz.m3u8";
    String testURl1="rtmp://172.18.231.247:1936/live/test1235";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video1);
        initView();
        initData();
    }

    private void initData() {
        ijkMediaPlayer = new IjkMediaPlayer();
        holder = sv_view.getHolder();
        holder.addCallback(this);
        try {
            ijkMediaPlayer.setDataSource(testURl1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ijkMediaPlayer.setOnPreparedListener(new OnprePareListener());
        ijkMediaPlayer.prepareAsync();
//
//        //使用videoview+mediaplayer播放
//        vv_view.setVideoPath(testURl2);
//        vv_view.start();
    }

    private void initView() {
        sv_view = findViewById(R.id.sv_view);
        vv_view = findViewById(R.id.vv_view);
    }
    class OnprePareListener implements IMediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            ijkMediaPlayer.start();
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder=holder;
        ijkMediaPlayer.setDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
