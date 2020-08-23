package com.hivescm.tms.consignor.nestscrolldemo.ui.scroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hivescm.tms.consignor.nestscrolldemo.R;
import com.hivescm.tms.consignor.nestscrolldemo.view.ScrollTestView;

public class ScrollerTestActivity extends AppCompatActivity {

    private ScrollTestView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_test);
        scroll = findViewById(R.id.scroller);
        scroll.setOnClickListener(v->{

            scroll.startScroll(0,0,-250,-250);
//            scroll.scrollTo(-250,-250);

        });
    }
}
