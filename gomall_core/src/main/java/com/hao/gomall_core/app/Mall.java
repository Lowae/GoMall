package com.hao.gomall_core.app;

import android.content.Context;

public class Mall {

    public static Configurator init(Context context){
        Configurator.getInstance()
                .getConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key){
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext(){
        return  getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

}
