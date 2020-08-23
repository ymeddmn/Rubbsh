package com.sitech.paas.scrollviewinscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * author  :mayong
 * function:
 * date    :2019-09-05
 **/
public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        getParent().getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }
}
