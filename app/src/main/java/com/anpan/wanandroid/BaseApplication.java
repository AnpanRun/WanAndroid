package com.anpan.wanandroid;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by AnpanRun on 2021/3/11.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
