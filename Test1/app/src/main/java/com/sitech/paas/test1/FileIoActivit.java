package com.sitech.paas.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class FileIoActivit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_io);
        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IoUtils.saveFile("哈哈哈航AA");
            }
        });
    }
}
