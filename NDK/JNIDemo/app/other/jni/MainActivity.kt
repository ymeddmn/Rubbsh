package com.example.demo.jni

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        btn_return_jnistr.text = getStringStr()
        btn_clide_printparams.setOnClickListener {
//            sendParamsToJni("点击按钮打印参数")
        }
        btn_jnistr_length.setOnClickListener {
//            getJniStrLength("哈哈哈")
        }
        btn_intarray.setOnClickListener {
            var array:Array<Int> = arrayOf(2)
//            sendIntArray(array)
        }
    }

//    external fun getStringStr(): String
//    external fun sendParamsToJni(str: String)
//    external fun getJniStrLength(str: String)
//    external fun sendIntArray(array: Array<Int>)

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

}
