package com.hao.gomall_core.delegates.web;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewInitializer {

    public WebView createWebView(WebView webView){
        WebView.setWebContentsDebuggingEnabled(true);
        //禁止水平横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);
        //屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        //初始化WebSetting
        final WebSettings settings = webView.getSettings();
        final String userAgent = settings.getUserAgentString();
        settings.setUserAgentString(userAgent + "GoMall");
        //隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        //禁止缩放
        settings.setSupportZoom(false);
        //文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        return webView;
    }
}
