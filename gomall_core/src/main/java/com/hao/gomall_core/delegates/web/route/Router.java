package com.hao.gomall_core.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.delegates.web.WebDelegate;
import com.hao.gomall_core.delegates.web.WebDelegateImpl;

public class Router {

    private Router() {

    }

    private static class RouterHolder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return RouterHolder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate delegate, String url) {
        //如果为电话协议
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }
        final MallDelegate topDelegate = delegate.getTopDelgate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);

        topDelegate.start(webDelegate);
        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("webView is null!");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getmWebView(), url);
    }

    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
