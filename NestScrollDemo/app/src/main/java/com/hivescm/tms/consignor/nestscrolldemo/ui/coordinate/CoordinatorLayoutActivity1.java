package com.hivescm.tms.consignor.nestscrolldemo.ui.coordinate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hivescm.tms.consignor.nestscrolldemo.R;
import com.hivescm.tms.consignor.nestscrolldemo.adapter.EspecialLongAdapter;

public class CoordinatorLayoutActivity1 extends AppCompatActivity {

    private RecyclerView recycle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        recycle = findViewById(R.id.coor_rv);
        toolbar = findViewById(R.id.toolbar);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(new EspecialLongAdapter(this));
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
    }
}
