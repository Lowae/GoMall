package com.hao.gomall.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.hao.gomall_core.delegates.web.event.Event;

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_SHORT).show();
        if (getAction().equals("test")){
            final WebView webView = getmWebVIew();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
