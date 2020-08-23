package com.example.demo.nestscroll.nest1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.NestedScrollingChild;
import android.util.AttributeSet;
import android.view.View;

public class IParent extends NestScrollParentImpl {
    private View child;

    public IParent(Context context) {
        super(context);
    }

    public IParent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
        child = getChildAt(0);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view1, int i) {
        return view instanceof NestedScrollingChild;
    }

    @Override
    public void onNestedPreScroll(@NonNull View view, int x, int y, @NonNull int[] consumed) {
        if (y > 0) {
            if (view.getY() < getHeight() - view.getHeight()) {
                view.offsetTopAndBottom(y);
            } else {
                offsetTopAndBottom(y);
            }
        } else {
            if (view.getY() < 0) {
                offsetTopAndBottom(y);
            } else {
                view.offsetTopAndBottom(y);
            }
        }

        if (x > 0) {
            if (view.getX() < getWidth() - view.getWidth()) {
                view.offsetLeftAndRight(x);
            } else {
                offsetLeftAndRight(x);
            }
        } else {
            if (view.getX() < 0) {
                offsetLeftAndRight(x);
            } else {
                view.offsetLeftAndRight(x);
            }
        }
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view1, int i) {
        super.onNestedScrollAccepted(view, view1, i);
    }
}
