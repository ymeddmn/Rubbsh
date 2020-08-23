package com.example.demo.gifdrawabletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageButton;

public class MainActivity extends AppCompatActivity {
    GifImageButton gifImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews()  {
        gifImageButton =(GifImageButton) findViewById(R.id.gifImageButton);

        gifImageButton.setImageResource(R.drawable.gif1);
        final MediaController mc = new MediaController(this);
        mc.setMediaPlayer( (GifDrawable) gifImageButton.getDrawable() );
        mc.setAnchorView( gifImageButton );
        gifImageButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick ( View v )
            {
                mc.show();
            }
        } );

    }
}
