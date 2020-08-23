package com.sitech.httptest

import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import android.content.pm.PackageManager
import android.content.pm.ApplicationInfo
import android.os.Looper
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.function.Consumer


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener({
            observer2()
        })

    }

    private fun observer2() {

        Observable.just(1,2,3,4).map{
            println("haha:"+it)
        }.subscribe()

    }

    private fun observer1() {
        Observable.create(object :ObservableOnSubscribe<Int>{
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(12)
                println("haha"+Thread.currentThread().id+":"+Looper.getMainLooper().thread.id)
            }
        }).observeOn(AndroidSchedulers.mainThread()).//表示在观察者在主线程观察数据
            subscribeOn(Schedulers.io()).//表示被观察者的数据执行在子程
            subscribe(object :Observer<Int>{
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Int) {
                btn.text=t.toString()
                println("haha"+Thread.currentThread().id+":"+Looper.getMainLooper().thread.id)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        })
    }

    override fun onPause() {
        super.onPause()
        println("暂停")
    }
}
