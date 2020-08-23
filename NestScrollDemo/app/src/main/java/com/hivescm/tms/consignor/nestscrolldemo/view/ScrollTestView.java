package com.hivescm.tms.consignor.nestscrolldemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by mayong on 2018/5/20.
 */

public class ScrollTestView extends LinearLayout {


    private Scroller scroller;

    public ScrollTestView(Context context) {
        super(context);
        init();
    }

    public ScrollTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        scroller = new Scroller(getContext());
    }

    /**
     * 开始滑动
     * @param startX
     * @param startY
     * @param dx
     * @param dy
     */
    public void startScroll(int startX, int startY, int dx, int dy) {
        scroller.startScroll(startX, startY, dx, dy);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
        }
    }
}
