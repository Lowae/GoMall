package com.hao.gomall_core.app;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

public class Configurator {

    private static final HashMap<Object, Object> CONFIGS = new HashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator(){
        CONFIGS.put(ConfigKeys.CONFIG_READY, false);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }


    private static class Holder{

        private static final Configurator INSTANCE = new Configurator();

    }

    public final void configure(){
        CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    public final Configurator withApiHost(String host){
        CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){

        INTERCEPTORS.addAll(interceptors);
        CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady){
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key){
        checkConfiguration();
        final Object value = CONFIGS.get(key);
        if (value == null){
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) CONFIGS.get(key);
    }

    final HashMap<Object, Object> getConfigs(){
        return CONFIGS;
    }
}
