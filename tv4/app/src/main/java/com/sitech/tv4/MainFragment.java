package com.sitech.tv4;

/**
 * @author wangyongmei
 * @describe describe
 * @time 2019/4/30 下午2:55
 */
import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.zip.Inflater;

public class MainFragment extends BrowseFragment {
    private static final String TAG = MainFragment.class.getSimpleName();
    private  View mview;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
//        WebView webView = mview.findViewById(R.id.web);
//        webView.loadUrl("http://he.sx.chinamobile.com/h/index.html#/communication");
//
//

        setupUIElements();

    }

     private  void setupUIElements(){

     }
}