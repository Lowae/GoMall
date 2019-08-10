package com.hao.gomall_core.app;

import java.util.WeakHashMap;

public class Configurator {

    private static final WeakHashMap<String, Object> CONFIGS = new WeakHashMap<>();

    private Configurator(){
        CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }


    private static class Holder{

        private static final Configurator INSTANCE = new Configurator();

    }

    public final void configure(){
        CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host){
        CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) CONFIGS.get(key.name());
    }

    final WeakHashMap<String, Object> getConfigs(){
        return CONFIGS;
    }
}
