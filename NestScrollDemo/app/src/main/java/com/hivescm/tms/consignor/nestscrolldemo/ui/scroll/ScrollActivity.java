package com.hivescm.tms.consignor.nestscrolldemo.ui.scroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hivescm.tms.consignor.nestscrolldemo.R;
import com.hivescm.tms.consignor.nestscrolldemo.adapter.ActivityAdapter;
import com.hivescm.tms.consignor.nestscrolldemo.adapter.EspecialLongAdapter;

public class ScrollActivity extends AppCompatActivity {

    private RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        recycle = findViewById(R.id.scroll_rv);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(new ActivityAdapter(this, "com.hivescm.tms.consignor.nestscrolldemo.ui.scroll"));
    }
}
