package com.mage.submarinegame

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap

/**
 * author  :mayong
 * function:
 * date    :2020-05-10
 **/
sealed class Bar(context: Context) {
    var h = 0
        set(value) {
            field = value
            dest = Rect(0, 0, w, h)
        }
    var w = 0
        set(value) {
            field = value
            dest = Rect(0, 0, w, h)
        }
    var x = 0
        set(value) {
            field = value
            view.x = field.toFloat()
        }
    var y = 0
    private val paint = Paint()
    protected abstract val src: Rect

    private lateinit var dest: Rect
    protected open val bmp = context.resources.getDrawable(R.mipmap.bar)!!.toBitmap()
    val view by lazy {
        BaseView(context) {
            it?.apply {
                //画bitmap
                drawBitmap(bmp, src, dest, paint)
//                drawBitmap(bmp,null,null,paint)
            }
        }
    }
}

/**
 * 顶部的柱子
 */
class UpBar(context: Context, container: ViewGroup) : Bar(context) {
    val _src by lazy {
        var recent = (1 - (h.toFloat() / container.height.toFloat()))
        var meHeight = bmp.height * recent
        Rect(0, meHeight.toInt(), bmp.width, bmp.height)
    }
    override val src
        get() = _src
}

/**
 * 下面的柱子
 */
class DownBar(context: Context, container: ViewGroup) : Bar(context) {

    override val bmp: Bitmap
        get() = super.bmp.let {
            Bitmap.createBitmap(it,0,0,it.width,it.height, Matrix().apply { postRotate(180.toFloat()) },true)
        }
    val _src by lazy {
        var meHeight = (bmp.height * (h.toFloat() / container.height.toFloat()))
        Rect(0, 0, bmp.width, meHeight.toInt())
    }
    override val src
        get() = _src
}

@SuppressLint("ViewConstructor")
class BaseView(context: Context, private val block: (Canvas?) -> Unit) : View(context) {
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        block(canvas)
    }
}