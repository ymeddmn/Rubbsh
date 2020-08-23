package com.mage.nestscrollelm;

import android.view.View;

/**
 * author  :mayong
 * function:
 * date    :2020-06-13
 **/
public class ElmNestViews {


    public View title;

    public void initView(ElmNestScrollLayout parent) {
        title = parent.findViewById(R.id.title);
    }
}
