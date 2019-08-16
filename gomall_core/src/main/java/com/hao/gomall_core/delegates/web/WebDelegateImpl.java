package com.hao.gomall_core.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hao.gomall_core.delegates.web.chromeclient.WebChromeClientImpl;
import com.hao.gomall_core.delegates.web.client.WebViewClientImpl;
import com.hao.gomall_core.delegates.web.route.RouteKeys;
import com.hao.gomall_core.delegates.web.route.Router;

public class WebDelegateImpl extends WebDelegate {

    public static WebDelegateImpl create(String url){
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getmWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getmUrl() != null){
            Router.getInstance().loadPage(this, getmUrl());
        }
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
