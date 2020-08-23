package com.example.demo.nestscroll.nest1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class IChild extends NestScrollChildImpl {
    private float lastY;
    private int consumed[] = new int[2];
    private int offset[] = new int[2];
    private int showHeight;
    private float lastX;

    public IChild(Context context) {
        super(context);
    }

    public IChild(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IChild(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
        helper.setNestedScrollingEnabled(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取该控件显示的高度
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        showHeight = getMeasuredHeight();


//        //获取该控件全部展示的高度
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
    @Override
    public boolean dispatchNestedPreScroll(int i, int i1, @Nullable int[] ints, @Nullable int[] ints1) {
        return super.dispatchNestedPreScroll(i, i1, ints, ints1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = event.getY();
                lastX = event.getX();
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                float detailY = event.getY() - lastY;
                float detailX = event.getX()-lastX;
                lastY = event.getY();
                lastX = event.getX();
                if ( dispatchNestedPreScroll((int) detailX, (int) detailY, consumed, offset)) {
//                    offsetTopAndBottom((int) (detailY-consumed[1]));
                }


                break;
        }

        return true;
    }

}
