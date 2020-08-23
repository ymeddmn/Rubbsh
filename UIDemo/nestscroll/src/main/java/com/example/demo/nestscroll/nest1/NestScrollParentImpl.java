package com.example.demo.nestscroll.nest1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class NestScrollParentImpl extends LinearLayout implements NestedScrollingParent {
    protected NestedScrollingParentHelper helper;

    public NestScrollParentImpl(Context context) {
        super(context);
        init();
    }

    protected void init() {
        helper = new NestedScrollingParentHelper(this);
    }

    public NestScrollParentImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NestScrollParentImpl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view1, int i) {
        return false;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view1, int i) {
        helper.onNestedScrollAccepted(view,view1,i);
    }

    @Override
    public void onStopNestedScroll(@NonNull View view) {
        helper.onStopNestedScroll(view);
    }

    @Override
    public void onNestedScroll(@NonNull View view, int i, int i1, int i2, int i3) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View view, int i, int i1, @NonNull int[] ints) {

    }

    @Override
    public boolean onNestedFling(@NonNull View view, float v, float v1, boolean b) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(@NonNull View view, float v, float v1) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }
}
