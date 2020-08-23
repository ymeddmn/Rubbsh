package com.sitech.paas.jnidemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sitech.paas.jnidemo1.R;

public class MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("native-lib");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        byte[] b = new byte[4];
        b[0]='a';
        b[1]='b';
        b[2]='c';
        b[3]='d';
        byte b1 = stringFromJNI(b);
        tv.setText(String.valueOf((char)b1));
//        tv.setText(String.valueOf(stringFromJNI1(2000)));
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native byte stringFromJNI(byte[] b);
//    public native int stringFromJNI1(int year);
}
