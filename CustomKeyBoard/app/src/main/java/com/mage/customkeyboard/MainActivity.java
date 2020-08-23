package com.mage.customkeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sitech.keyboard.KeyBoardDialogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText view = findViewById(R.id.et);
        KeyBoardDialogUtils.getInstance(this).show(view);
    }
}
