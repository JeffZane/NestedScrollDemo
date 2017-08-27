package com.demo.yzg.nestedscroll;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
