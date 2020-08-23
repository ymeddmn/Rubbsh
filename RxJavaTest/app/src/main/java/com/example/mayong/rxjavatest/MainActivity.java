package com.example.mayong.rxjavatest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void repeatWhen() {
        Observable.just(1).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            int index = 0;

            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                if (index < 3) {
                    index++;
                    return objectObservable;
                } else {
                    return null;
                }
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                print(integer);
            }
        });
    }

    private void repeat() {//将一个序列重复发送三次
        Observable.range(1, 5).repeat(3).subscribe(data -> {
            print(data);
        });
    }

    private void range() {
        Observable.range(1, 5).subscribe(data -> {
            print(data);
        });
    }

    private void interval() {
        Observable.interval(3000, 1000, TimeUnit.MILLISECONDS).subscribe(time -> {
            print(time);
        });
    }

    private void from() {//遍历列表
        ArrayList<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        Observable.fromIterable(array).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                print(integer);
            }
        });
    }

    String a = "hello";

    private void differ() {
        Observable<String> defer = Observable.defer(new Callable<ObservableSource<String>>() {

            @Override
            public ObservableSource<String> call() throws Exception {
                System.out.println("创建defer" + a);//这里也是subscribe被调用的时候才创建
                return Observable.just(a);
            }
        });
        a = "hello world defer";
        defer.subscribe(new Consumer<String>() {//观察者一订阅就会发消息，否则不会发消息
            @Override
            public void accept(String s) throws Exception {
                System.out.println(a);
            }
        });
    }

    private void print(Object o) {
        System.out.println("输出是：" + o);
    }
}
