package com.hao.gomall_core.delegates.web;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ToastUtils;
import com.hao.gomall_core.delegates.web.event.Event;
import com.hao.gomall_core.delegates.web.event.EventManager;

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

    @JavascriptInterface
    public void shared(){
        AlertDialog dialog = new AlertDialog.Builder(mWebDelegate.getContext())
                .setTitle("分享")
                .setPositiveButton("确认跳转", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtils.showShort("分享成功！");
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
        dialog.show();
    }
}
