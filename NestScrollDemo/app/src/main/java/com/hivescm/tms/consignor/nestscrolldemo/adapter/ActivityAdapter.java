package com.hivescm.tms.consignor.nestscrolldemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mayong on 2018/5/25.
 */

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityHolder> {
    private String content;
    private List<ActivityInfo> activities;
    private Context context;

    public ActivityAdapter(Context context, String content) {
        this.context = context;
        activities = new ArrayList<>();
        this.content = content;
        try {
            PackageInfo datas = context.getPackageManager().getPackageInfo("com.hivescm.tms.consignor.nestscrolldemo", PackageManager.GET_ACTIVITIES);
            ActivityInfo[] temp = datas.activities;
            for (ActivityInfo info : temp) {
                if (info.name.contains(content)) {
                    activities.add(info);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public ActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Button button = new Button(context);

        ActivityHolder holder = new ActivityHolder(button);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityHolder holder, int position) {
        ActivityInfo activity = activities.get(position);
        CharSequence label = activity.loadLabel(context.getPackageManager());
        String name = activity.name;
        if (name.contains(content)) {
            Button btn = (Button) holder.itemView;
            btn.setText(label);
            btn.setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.setClassName(activity.packageName, activity.name);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public static class ActivityHolder extends RecyclerView.ViewHolder {

        public ActivityHolder(View itemView) {
            super(itemView);
        }
    }
}
