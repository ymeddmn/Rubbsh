package com.mage.nestscrollelm;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.widget.NestedScrollView;

/**
 * author  :mayong
 * function:NestScrollingParent
 * date    :2020-06-13
 **/
public class ElmNestScrollLayout extends FrameLayout implements NestedScrollingParent2 {

    private NestedScrollingParentHelper parentHelper;
    //    public ElmNestViews nestViews;
    private int titleHeight;//标题栏高度
    private NestedScrollView scroll;//滑动控件
    private View tabTitle;
    private float scrollInittranslationY;//滑动控件的初始y距离
    private View title;//红色的title
    private int tabHeight;//tab的高度
    private float tabInitTransY;//tab的初始偏移量

    public ElmNestScrollLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ElmNestScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ElmNestScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        parentHelper = new NestedScrollingParentHelper(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        scroll = findViewById(R.id.scroll);
        title = findViewById(R.id.title);
        tabTitle = findViewById(R.id.tab_title);
        scrollInittranslationY = scroll.getTranslationY();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        titleHeight = title.getMeasuredHeight();
        tabHeight = tabTitle.getMeasuredHeight();
        tabInitTransY = tabTitle.getTranslationY();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return true;//返回true表示父控件接受了嵌套滑动，这里当然要接受
    }

    /**
     * 子view滑动前先通知父view
     */
    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        parentHelper.onNestedScrollAccepted(child, target, axes, type);
    }

    /**
     * 子view马上就要滑动了，告诉父view让父view先滑动
     * @param target
     * @param dx >0向左滑动、小于零向右滑动
     * @param dy 大于0向上滑动、小于零向下滑动
     * @param consumed
     * @param type
     */
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        log("onNestedPreScroll：----dx：" + dx + "----dy:" + dy + "-----consumed[0]-----:" + consumed[0] + ":" + consumed[1] + ":" + type);

        if (dy > 0) {//向上滑动
           scrollOperate(target,dx,dy,consumed,type);
        } else {//向下滑动
            int verticalY = scroll.computeVerticalScrollOffset();
            if (tabTitle.getTranslationY() <= 0 && verticalY > 0) {//绿色的tab到达了顶部，并且scrollview是有向上滑动距离的，这个时候先滑动scrollview里面的内容
                if (target instanceof NestedScrollView) {
                    //模拟DONW事件停止滚动，注意会触发onNestedScrollAccepted()
//                    MotionEvent motionEvent = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0);
//                    target.onTouchEvent(motionEvent);
                    scroll.scrollBy(0,dy);
                }
            } else {
                scrollOperate(target,dx,dy,consumed,type);
            }

        }
    }

    /**
     * 处理scrollview和title和tabtitle之间的滑动关系
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     * @param type
     */
    private void scrollOperate(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        float tabTransY = tabTitle.getTranslationY() - dy;//
        float scrollTransY = scroll.getTranslationY();
        float titleTransY = title.getTranslationY();
        if (tabTransY >= 0 && tabTransY <= tabInitTransY) {
            consumed[1] = dy;
            scrollTransY = scrollTransY - dy;
        } else if (tabTransY < 0) {
            tabTransY = 0;
            scrollTransY = tabTransY;
        } else if (tabTransY > tabInitTransY) {
            tabTransY = tabInitTransY;
            scrollTransY = scrollInittranslationY;
        }
        if (tabTransY < titleHeight && tabTransY > 0) {
            titleTransY = titleTransY - dy;
        } else if (tabTransY <= 0) {
            titleTransY = -titleHeight;
        } else if (tabTransY > titleHeight) {
            titleTransY = 0;
        }
        title.setTranslationY(titleTransY);
        scroll.setTranslationY(scrollTransY);
        tabTitle.setTranslationY(tabTransY);
    }

    /**
     * 子view滑动完了，如果有剩余距离没有消耗告诉父view，让父view继续滑动
     */
    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        log("onNestedScroll：dxConsumed：" + dxConsumed + "---dyconsumed:" + dyConsumed + "---dxUnconsumed:" + dxUnconsumed + "---dyUnconsume:" + dyUnconsumed + ":" + type);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        parentHelper.onStopNestedScroll(target, type);
    }


    private void log(String msg) {
        Log.e("NestParent", msg);
    }
}
