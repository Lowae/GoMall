package com.hao.gomall_core.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.hao.gomall_core.app.ConfigKeys;
import com.hao.gomall_core.app.Mall;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public abstract class WebDelegate extends MallDelegate implements IWebViewInitializer {

    private WebView mWebView;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl;
    private boolean mIsWebViewAvailable = true;
    private MallDelegate mTopDelgate;

    public WebDelegate() {
    }

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference = new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                final String name = Mall.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE);
                mWebView.addJavascriptInterface(MallWebInterface.create(this), name);
                mIsWebViewAvailable = true;
            }else {
                throw new NullPointerException("Initializer is null!");
            }
        }
    }

    public void setTopDelegate(MallDelegate delegate){
        mTopDelgate = delegate;
    }

    public MallDelegate getTopDelgate() {
        if(mTopDelgate == null){
            mTopDelgate = this;
        }
        return mTopDelgate;
    }

    public WebView getmWebView(){
        if (mWebView == null){
            throw new NullPointerException("WebView is null!");
        }
        return mIsWebViewAvailable ? mWebView : null;
    }

    public String getmUrl(){
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null){
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null){
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null){
            mWebView.removeAllViews();
            mWebView.destroy();
        }
    }
}
