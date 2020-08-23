package com.example.windowdemo3;

import android.app.Application;

public class App extends Application {
    public static App app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        registerActivityLifecycleCallbacks(new AppLifeCycle());
    }

    public static App getInstance() {
        return app;
    }
}
