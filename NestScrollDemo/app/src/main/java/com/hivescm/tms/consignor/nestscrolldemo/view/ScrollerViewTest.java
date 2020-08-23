package com.hivescm.tms.consignor.nestscrolldemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by mayong on 2018/5/19.
 */

public class ScrollerViewTest extends View {


    private Scroller mScroller;

    public ScrollerViewTest(Context context) {
        super(context);
        init();
    }

    public ScrollerViewTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollerViewTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mScroller = new Scroller(getContext());
    }



    public void smoothScrollBy(int dx, int dy) {

        // 设置mScroller的滚动偏移量
        // mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        mScroller.startScroll(0, 0, dx, dy);
        invalidate();// 这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    @Override
    public void computeScroll() {

        // 先判断mScroller滚动是否完成
        if (mScroller.computeScrollOffset()) {

            // 这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

            // 必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
        super.computeScroll();
    }
    private float lastX,lastY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                float curX = event.getX();
                float curY = event.getY();
                float dx = curX - lastX;
                float dy = curY - lastY;
                offsetTopAndBottom((int) dy);
                offsetLeftAndRight((int) dx);
                break;
        }

        return true;
    }
}
