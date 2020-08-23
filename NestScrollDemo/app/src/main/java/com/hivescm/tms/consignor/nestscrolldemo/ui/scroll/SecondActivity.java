package com.hivescm.tms.consignor.nestscrolldemo.ui.scroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hivescm.tms.consignor.nestscrolldemo.R;
import com.hivescm.tms.consignor.nestscrolldemo.view.ScrollerViewTest;

public class SecondActivity extends AppCompatActivity {
    ScrollerViewTest scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        scroll = findViewById(R.id.scroll);
        scroll.setOnClickListener(v->{
//            scroll.smoothScrollBy(0,40);
            scroll.offsetTopAndBottom(10);
        });
    }
}
