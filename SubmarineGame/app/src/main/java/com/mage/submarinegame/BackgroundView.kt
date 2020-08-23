package com.mage.submarinegame

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import java.util.*

/**
 * author  :mayong
 * function:
 * date    :2020-05-10
 **/
class BackgroundView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private  var timer: Timer?=null
    private var barsList = mutableListOf<Bars>()
    private val stepLen = 50//前后两个柱子之间的距离
    private val distanceBetween by lazy {
        //两个柱子的之间的距离
        0.5 * height
    }
    private val _barWidth by lazy {
        0.22 * width
    }
    private val _anims = mutableListOf<ValueAnimator>()
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        barsList.flatMap { listOf(it.upBar, it.downBar) }.forEach {
            val w = it.view.measuredWidth
            var h = it.view.measuredHeight
            when (it) {
                is UpBar -> it.view.layout(0, 0, x.toInt() + w, y.toInt() + h)
                else -> it.view.layout(0, height - h, w, height)
            }
        }
    }

    fun start() {
        stop()
        _clear()
        Timer().also { timer=it }.schedule(object :TimerTask(){
            override fun run() {
               post {
                   val bars = _createBar(barsList.lastOrNull())
                   barsList.add(bars)
                   _addBar(bars)
                   _moveBars(bars)
               }
            }

        }, 0,
            BAR_APPEAR_INTERVAL_MILLIS)
    }

    fun stop(){
        _anims.forEach{
            it.cancel()
        }
        _anims.clear()
        timer?.apply { cancel() }

    }
    private fun _moveBars(bars: Bars) {
        _anims.add(
            ValueAnimator.ofFloat(width.toFloat(), -bars.upBar.w.toFloat())
                .apply {
                    addUpdateListener {
                        bars.asArray().forEach { bar ->
                            bar.x = (it.animatedValue as Float).toInt()
                            if (bar.x + bar.w <= 0) {
                                post { removeView(bar.view) }
                            }
                        }
                    }

                    duration = 4000
                    interpolator = LinearInterpolator()
                    start()
                })
    }

    private fun _createBar(pre: Bars?): Bars {
        var upBar = UpBar(context,this).apply {
            h = 400
            w = _barWidth.toInt()
        }
        var downBar = DownBar(context,this).apply {
            y = upBar.h + distanceBetween.toInt()
            h = height - distanceBetween.toInt() - upBar.h
            w = _barWidth.toInt()
        }
        return Bars(upBar, downBar)
    }

    private fun _addBar(bars: Bars) {
        bars.asArray().forEach {
            addView(it.view, ViewGroup.LayoutParams(it.w, it.h))
        }
    }

    private fun _clear() {
        barsList.clear()
        removeAllViews()
    }
}

internal data class Bars(var upBar: UpBar, var downBar: DownBar)

private fun Bars.asArray() = arrayOf(upBar, downBar)

private const val BAR_MOVE_DURATION_MILLIS = 4000L

private const val BAR_APPEAR_INTERVAL_MILLIS = (BAR_MOVE_DURATION_MILLIS / 2.2).toLong()

internal const val FIRST_APPEAR_DELAY_MILLIS = 3000L