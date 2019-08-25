package com.hao.gomall_core.utils.callback;

import java.util.WeakHashMap;

public class CallbackManager {

    private static final WeakHashMap<Object, IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    private CallbackManager(){

    }


    private static class CallbackManagerHolder{
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance() {
        return CallbackManagerHolder.INSTANCE;
    }

    public CallbackManager addCallback(Object tag, IGlobalCallback callback){
        CALLBACKS.put(tag, callback);
        return this;
    }

    public IGlobalCallback getCallback(Object tag){
        return CALLBACKS.get(tag);
    }

}
