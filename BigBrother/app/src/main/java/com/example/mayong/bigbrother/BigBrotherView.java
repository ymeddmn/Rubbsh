package com.example.mayong.bigbrother;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by mayong on 2018/1/25.
 */

public class BigBrotherView extends ViewGroup {
    private View child1;
    private View child2;
    private int height1;
    private int topY;

    public BigBrotherView(Context context) {
        super(context);
        init(context);
    }


    public BigBrotherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BigBrotherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        child1 = getChildAt(0);
        child2 = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        child1.measure(widthMeasureSpec,getChildMeasureSpec(widthMeasureSpec,0, (int) (getResources().getDisplayMetrics().density*50)));
        child2.measure(widthMeasureSpec,getChildMeasureSpec(widthMeasureSpec,0,LayoutParams.WRAP_CONTENT));
        height1 = child1.getMeasuredHeight();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        child1.layout(0, topY-height1, child1.getMeasuredWidth(), child1.getMeasuredHeight()+topY-height1);
        child2.layout(0, topY + child1.getMeasuredHeight()-height1, child2.getMeasuredWidth(), child1.getMeasuredHeight() + topY + child2.getMeasuredHeight()-height1);
    }

    public void setTopY(int y) {
        this.topY = y;
        requestLayout();
    }
}
