package com.hao.gomall;

import android.app.Application;

import com.hao.gomall.event.TestEvent;
import com.hao.gomall.mall.db.DBManager;
import com.hao.gomall.mall.widgt.icon.FontEcModule;
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
                .withJavascriptInterface("GoMall")
                .withWebEvent("test", new TestEvent())
                .withWeChatAppSecret("7aef84dd87474207ffbc4ab44025d559")
                .withWeChatAppId("wxf3c02e793c81f464")
                .configure();
        DBManager.getInstance().init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }


}
