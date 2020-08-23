package com.sitech.paas.siplugindemo;

import android.app.Application;
import android.content.Context;
import com.sitech.paas.tracer.SitechApm;
import org.jetbrains.annotations.NotNull;

//import com.example.crashandler.CrashHandler;

public class CrashApplication extends Application {
    private static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SitechApm.INSTANCE.initialize(this);
    }

    @NotNull
    public static Context getConstex() {
        return app;
    }
}
