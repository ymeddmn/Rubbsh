package com.hivescm.tms.consignor.nestscrolldemo.ui.scroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hivescm.tms.consignor.nestscrolldemo.R;

/*

left right  x y translateX translateY 区别
 */
public class ViewPositionActivity extends AppCompatActivity {

    private TextView iv1,iv2,tv3,tv4,tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_position);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        initListener();
    }

    private void initListener() {
        iv1.setOnClickListener(v -> {
            iv1.setTranslationX(160);//translateX是view相对于初始left的偏移量
            // getX = getLeft()+translateX
        });

        iv2.setOnClickListener(v->{
            iv2.setX(300);
        });

        tv3.setOnClickListener(v->{
            tv3.setScrollX(-100);
        });

        tv4.setOnClickListener(v->{
            tv4.setLeft(12);
        });

        tv5.setOnClickListener(v->{
            Toast.makeText(ViewPositionActivity.this,"点击",Toast.LENGTH_LONG).show();
        });
        tv5.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                Toast.makeText(ViewPositionActivity.this,"however",Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }
}
