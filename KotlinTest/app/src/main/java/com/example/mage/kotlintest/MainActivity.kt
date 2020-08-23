package com.example.mage.kotlintest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_1.setOnClickListener {
            m1()
        }
        btn2.setOnClickListener {
            m2()
        }
        btn3.setOnClickListener {
            m3()
        }
        btn4.setOnClickListener {
            m4()
        }
        var a = SparseArray<String>()
        a.put(1,"1")
        a.put(5,"5")
        a.put(10,"10")
        a.put(4,"4")
        a.put(3,"3")
        a
        println("haha"+a.indexOfKey(1))
        println("haha"+a.indexOfKey(3))
        println("haha"+a.indexOfKey(4))
        println("haha"+a.indexOfKey(5))
        println("haha"+a.indexOfKey(10))

    }

    /**
     * kotlin数组循环操作
     */
    private fun m4() {
        var array = listOf<Int>(1, 2, 3, 4, 5, 6, 7)
        array
            .filter {
                it > 3
            }.sortedBy {
                it
            }.map { it + 100 }.forEach {
                println("haha:" + it)
            }
    }

    private fun m3() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = async {
                getData()
            }

            withContext(Dispatchers.Main) {
                println("haha" + data.await())
                btn3.text = "延迟两秒获得结果"

            }
        }
    }

    private fun m2() {
        runBlocking {
            // 但是这个表达式阻塞了主线程
            delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
            btn2.text = "主线程被阻塞了两秒"
        }
    }

    private fun m1() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = async {
                getData()
            }
            launch(Dispatchers.Main) {
                btn_1.text = data.await().toString()
            }
        }
    }

    private suspend fun getData(): Int {
        delay(1000)
        return 1
    }
}
