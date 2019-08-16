package com.hao.gomall_core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.hao.gomall_core.app.Mall;
import com.hao.gomall_core.delegates.web.event.Event;
import com.hao.gomall_core.delegates.web.event.EventManager;
import com.hao.gomall_core.utils.MallLogger;

public final class MallWebInterface {

    private final WebDelegate mWebDelegate;

    public MallWebInterface(WebDelegate webDelegate) {
        mWebDelegate = webDelegate;
    }

    static MallWebInterface create(WebDelegate webDelegate) {
        return new MallWebInterface(webDelegate);
    }

    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null){
            event.setAction(action);
            event.setDelegaate(mWebDelegate);
            event.setContext(mWebDelegate.getContext());
            event.setUrl(mWebDelegate.getmUrl());
            return event.execute(params);
        }
        return null;
    }
}
