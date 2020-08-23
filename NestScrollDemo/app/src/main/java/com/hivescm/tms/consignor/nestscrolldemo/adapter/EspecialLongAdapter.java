package com.hivescm.tms.consignor.nestscrolldemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by mayong on 2018/5/25.
 */

public class EspecialLongAdapter extends RecyclerView.Adapter<EspecialLongAdapter.EspecialLongHolder> {

    private Context context;

    public EspecialLongAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public EspecialLongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Button button = new Button(context);
        return new EspecialLongHolder(button);
    }

    @Override
    public void onBindViewHolder(@NonNull EspecialLongHolder holder, int position) {
        Button btn = (Button) holder.itemView;
        btn.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return 1000;
    }

    public static class EspecialLongHolder extends RecyclerView.ViewHolder {

        public EspecialLongHolder(View itemView) {
            super(itemView);
        }
    }
}
