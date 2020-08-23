package com.hivescm.tms.consignor.nestscrolldemo.ui.coordinate;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.hivescm.tms.consignor.nestscrolldemo.R;
import com.hivescm.tms.consignor.nestscrolldemo.adapter.EspecialLongAdapter;

public class CollapsingToolbarLayoutRecyclerViewActivity extends AppCompatActivity {

    private CollapsingToolbarLayout mCollapsingLayout;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private ImageView mImg;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout);
        initView();
    }

    private void initView() {
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.id_coordinatorlayout_collapsing_layout);

        mToolbar = (Toolbar) findViewById(R.id.id_toolbar_collapse);
        mImg = (ImageView) findViewById(R.id.id_img_collapse);
        mImg.setImageResource(R.mipmap.ic_launcher);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mCollapsingLayout = (CollapsingToolbarLayout) findViewById(R.id.id_collapselayout);
        mCollapsingLayout.setTitle("CollapsingToolbarLayout");
        mCollapsingLayout.setCollapsedTitleTextColor(Color.WHITE);
        mCollapsingLayout.setExpandedTitleColor(Color.YELLOW);


        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview_collapse);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new EspecialLongAdapter(this));
    }
}
