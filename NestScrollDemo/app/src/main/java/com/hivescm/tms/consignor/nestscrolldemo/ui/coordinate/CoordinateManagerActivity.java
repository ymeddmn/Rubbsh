package com.hivescm.tms.consignor.nestscrolldemo.ui.coordinate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hivescm.tms.consignor.nestscrolldemo.R;
import com.hivescm.tms.consignor.nestscrolldemo.adapter.ActivityAdapter;

public class CoordinateManagerActivity extends AppCompatActivity {
    RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate_manager);
        recycle = findViewById(R.id.rv);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(new ActivityAdapter(this, "coordinate"));
    }
}
