package com.hao.gomall;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.hao.gomall.mall.db.DBManager;
import com.hao.gomall.mall.icon.FontEcModule;
import com.hao.gomall_core.app.Mall;
import com.hao.gomall_core.net.interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MallApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Mall.init(this.getApplicationContext())
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://169.254.7.30:8080/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
        DBManager.getInstance().init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
//        initStetho();
        Stetho.initializeWithDefaults(this);
    }


    private void initStetho(){
        Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build());
    }
}
