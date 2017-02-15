package com.itis.androidlab.service;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class App extends Application {

    public static Context context;
    public static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler(Looper.getMainLooper());
    }

}
