package com.hao.gomall_core.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.delegates.web.WebDelegate;

public abstract class Event implements IEvent{

    private Context mContext;
    private String mAction;
    private WebDelegate mDelegaate;
    private String mUrl;
    private WebView mWebVIew;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public MallDelegate getDelegaate() {
        return mDelegaate;
    }

    public void setDelegaate(WebDelegate mDelegaate) {
        this.mDelegaate = mDelegaate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public WebView getmWebVIew() {
        return mDelegaate.getmWebView();
    }

    public void setmWebVIew(WebView mWebVIew) {
        this.mWebVIew = mWebVIew;
    }
}
