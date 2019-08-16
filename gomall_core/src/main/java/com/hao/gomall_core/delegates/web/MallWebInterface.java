package com.hao.gomall_core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;

public class MallWebInterface {

    private final WebDelegate mWebDelegate;

    public MallWebInterface(WebDelegate webDelegate) {
        mWebDelegate = webDelegate;
    }

    static MallWebInterface create(WebDelegate webDelegate) {
        return new MallWebInterface(webDelegate);
    }

    @JavascriptInterface
    public String enent(String params) {
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
