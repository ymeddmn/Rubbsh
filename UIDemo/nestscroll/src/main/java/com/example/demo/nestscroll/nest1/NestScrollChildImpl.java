package com.example.demo.nestscroll.nest1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class NestScrollChildImpl extends LinearLayout implements NestedScrollingChild {
    protected NestedScrollingChildHelper helper;

    public NestScrollChildImpl(Context context) {
        super(context);
        init();
    }

    public NestScrollChildImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NestScrollChildImpl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        helper = new NestedScrollingChildHelper(this);
    }

    @Override
    public void setNestedScrollingEnabled(boolean b) {
        helper.setNestedScrollingEnabled(b);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return helper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int i) {
        return helper.startNestedScroll(i);
    }

    @Override
    public void stopNestedScroll() {
        helper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return helper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int i, int i1, int i2, int i3, @Nullable int[] ints) {
        return helper.dispatchNestedScroll(i, i1, i2, i3, ints);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {


        return helper.dispatchNestedPreScroll(dx,dy,consumed,offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float v, float v1, boolean b) {
        return helper.dispatchNestedFling(v, v1, b);
    }

    @Override
    public boolean dispatchNestedPreFling(float v, float v1) {
        return helper.dispatchNestedPreFling(v, v1);
    }
}
