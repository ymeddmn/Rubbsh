package com.example.messagetest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.widget.Toast;

public class DefaultSMSV4Fragment extends Fragment {
    private FController controller;
    private FController.MServiceReceiver mReceiver01;
    private FController.MServiceReceiver mReceiver02;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setTarget(FController fController) {
        this.controller = fController;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        controller.requestPermission(getActivity());
    }



    @Override
    public void onStart() {
        super.onStart();
        IntentFilter mFilter01;
        mFilter01 = new IntentFilter(FController.SMS_SEND_ACTIOIN);
        mReceiver01 = new FController.MServiceReceiver(getActivity());
        getActivity().registerReceiver(mReceiver01, mFilter01);
        /* 自定义IntentFilter为DELIVERED_SMS_ACTION Receiver */
        mFilter01 = new IntentFilter(FController.SMS_DELIVERED_ACTION);
        mReceiver02 = new FController.MServiceReceiver(getActivity());
        getActivity().registerReceiver(mReceiver02, mFilter01);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().unregisterReceiver(mReceiver01);
        getActivity().unregisterReceiver(mReceiver02);
    }
}
