package com.example.receiver;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HaHaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ha_ha);
        RecyclerView recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                Button btn=new Button(HaHaActivity.this);
                return new IHolder(btn);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                IHolder holder = (IHolder) viewHolder;
                holder.btn.setText(i+"");

            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });
    }
    static class IHolder extends RecyclerView.ViewHolder{
        public Button btn;
        public IHolder(@NonNull View itemView) {
            super(itemView);
            btn = (Button) itemView;
        }
    }
}
