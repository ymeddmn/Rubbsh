package com.hivescm.tms.consignor.demo;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.hivescm.tms.consignor.demo.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityMainBinding binding =  DataBindingUtil.setContentView(this,R.layout.activity_main);
//        binding.setHaha("sdf");
//        binding.bill.setOnClickListener(v->{
//            binding.bill.setText("销退单");
//            System.out.println("类型"+binding.getFlag());
//        });
//        binding.setFlag(1);

    }
}
