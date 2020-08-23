package com.hivescm.tms.consignor.nestscrolldemo.ui.scroll;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hivescm.tms.consignor.nestscrolldemo.R;

public class ThirdActivity extends AppCompatActivity {

    private NestedScrollView sv;
    private TextView tvTop;
    private FloatingActionButton btn;
    private RelativeLayout container, parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        sv = findViewById(R.id.sv);
        tvTop = findViewById(R.id.tvTop);
        btn = findViewById(R.id.fb_bottom);
        container = findViewById(R.id.ll_container);
        parent = findViewById(R.id.rl_container);
        initListener();
    }

    private void initListener() {
        sv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int dx = scrollX - oldScrollX;
                int dy = scrollY - oldScrollY;
                tvTop.offsetLeftAndRight(-dx);
                tvTop.offsetTopAndBottom(-dy);
                if (btn.getY() <= parent.getBottom()) {
                    container.scrollTo(0, parent.getBottom());
                } else {
                    container.scrollBy(0, -dy);
                }
            }
        });
    }
}
