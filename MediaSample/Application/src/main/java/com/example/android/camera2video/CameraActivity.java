/*
 * Copyright 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.camera2video;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class CameraActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button mSwitchMode;
    private GridView mModeGrid;
    private ArrayList<String> mData = null;
    private BaseAdapter mAdapter = null;
    private String[] mModeList = {"60fps", "120fps", "superslow"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initView();
    }

    private void initView() {
        mSwitchMode = (Button)findViewById(R.id.selectMode);
        mSwitchMode.setOnClickListener(this);
        mModeGrid = (GridView)findViewById(R.id.modegrid);
        mData = new ArrayList<String>();
        for (String mode:mModeList) {
            mData.add(mode);
        }
        mAdapter = new MyAdapter<String>(mData, R.layout.item_grid) {

            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.txt_icon, obj);
            }
        };
        mModeGrid.setAdapter(mAdapter);
        mModeGrid.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectMode:
                if (mModeGrid.isShown()) {
                    mModeGrid.setVisibility(View.INVISIBLE);
                } else {
                    mModeGrid.setVisibility(View.VISIBLE);
                }
                break;
            default:
        }
    }
    private View mCurrentSelectView;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mCurrentSelectView != null) {
            mCurrentSelectView.setBackgroundColor(Color.parseColor("#50424242"));
        }
        Log.d("ItemClick", "onItemClick: tag " +  view.getTag());
        switch(mModeList[position]) {
            case "60fps":
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, Camera2VideoFragment.newInstance())
                        .commit();
                break;
            case "120fps":
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, Camera2VideoFragmentHighSpeed.newInstance())
                        .commit();
                break;
            case "superslow":
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, Camera2VideoFragmentSuperSlow.newInstance())
                        .commit();
                break;
            default:
        }
        mCurrentSelectView = view;
        mCurrentSelectView.setBackgroundColor(Color.parseColor("#424242"));
    }
}
