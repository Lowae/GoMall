package com.hao.gomall;

import android.app.Application;

import com.hao.gomall_core.app.Mall;
import com.hao.gomall_core.net.interceptors.DebugInterceptor;

public class MallApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Mall.init(this.getApplicationContext())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
    }
}
