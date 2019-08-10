package com.hao.gomall;

import android.app.Application;

import com.hao.gomall_core.app.Mall;

public class MallApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Mall.init(this)
                .configure();
    }
}
