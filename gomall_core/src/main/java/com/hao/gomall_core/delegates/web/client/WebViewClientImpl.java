package com.hao.gomall_core.delegates.web.client;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hao.gomall_core.delegates.web.WebDelegate;
import com.hao.gomall_core.delegates.web.route.Router;
import com.hao.gomall_core.utils.MallLogger;

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate webDelegate;

    public WebViewClientImpl(WebDelegate webDelegate) {
        this.webDelegate = webDelegate;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        MallLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(webDelegate, url);
    }
}
