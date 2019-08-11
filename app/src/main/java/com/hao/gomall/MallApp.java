package com.hao.gomall;

import android.app.Application;

import com.hao.gomall.mall.icon.FontEcModule;
import com.hao.gomall_core.app.Mall;
import com.hao.gomall_core.net.interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class MallApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Mall.init(this.getApplicationContext())
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
    }
}
