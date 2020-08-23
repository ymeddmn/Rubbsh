package com.example.messagetest;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.widget.Toast;

public class DefaultSMSFragment extends Fragment {
    private FController controller;
    private FController.MServiceReceiver mReceiver01;
    private FController.MServiceReceiver mReceiver02;

    public DefaultSMSFragment() {
    }

    public void setTarget(FController fController) {
        this.controller = fController;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        controller.requestPermission(getActivity());
    }


    public void mMakeTextToast(String str, boolean isLong) {
        if (isLong == true) {
            Toast.makeText(getActivity(), str, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter mFilter01;
        mFilter01 = new IntentFilter(FController.SMS_SEND_ACTIOIN);
        mReceiver01 = new FController.MServiceReceiver(getActivity());
        getActivity().registerReceiver(mReceiver01, mFilter01);
        mFilter01 = new IntentFilter(FController.SMS_DELIVERED_ACTION);
        mReceiver02 = new FController.MServiceReceiver(getActivity());
        getActivity().registerReceiver(mReceiver02, mFilter01);
    }

}

