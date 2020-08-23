package com.example.sitechapm.log

import android.util.Log

/**
 * author  :mayong
 * function:
 * date    :2019-06-26
 **/

fun  e(datas: List<String>) {
    for (i in datas) {
        Log.e("打印参数", i)
    }
}