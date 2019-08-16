package com.hao.gomall_core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hao.gomall_core.app.Mall;
import com.hao.gomall_core.delegates.web.IPageLoadListener;
import com.hao.gomall_core.delegates.web.WebDelegate;
import com.hao.gomall_core.delegates.web.route.Router;
import com.hao.gomall_core.ui.MallLoader;
import com.hao.gomall_core.utils.MallLogger;

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate webDelegate;
    private IPageLoadListener mPageLoadListener;
    private static final Handler HANDLER = Mall.getHandler();

    public void setPageLoadListener(IPageLoadListener pageLoadListener){
        this.mPageLoadListener = pageLoadListener;
    }

    public WebViewClientImpl(WebDelegate webDelegate) {
        this.webDelegate = webDelegate;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        MallLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(webDelegate, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mPageLoadListener != null){
            mPageLoadListener.onLoadStart();
        }
        MallLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mPageLoadListener !=  null){
            mPageLoadListener.onLoadFinished();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                MallLoader.stopLoading();
            }
        }, 1000);
    }
}
