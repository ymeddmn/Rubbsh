package com.example.demo.nestscroll.nest2

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

class HeaderBehavior(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {


        return dependency is RecyclerView
    }


    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return true
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        if (target is RecyclerView) {

            val visibleItemPosition = (target.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            if (visibleItemPosition == 0 && canScroll(child, dy)) {

                if (child.y < -child.height) {
                    child.y = (-child.height).toFloat()
                } else if (child.y > 0) {
                    child.y = 0f
                } else {
                    child.y = child.y - dy
                }
                consumed[1]=dy
            }
        }
    }

    private fun canScroll(child: View, dy: Int): Boolean {
        return !(dy > 0 && child.y.toInt() == -child.height)
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
    }
}