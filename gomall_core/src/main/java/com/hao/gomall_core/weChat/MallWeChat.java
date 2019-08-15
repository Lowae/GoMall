package com.hao.gomall_core.weChat;

import android.app.Activity;

import com.hao.gomall_core.app.ConfigKeys;
import com.hao.gomall_core.app.Mall;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class MallWeChat {

    public static final String APP_ID = Mall.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Mall.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI IWXAPI;

    private static final class MallWeChatHolder{
        private static final MallWeChat INSTANCE = new MallWeChat();
    }

    public static MallWeChat getInstance() {
        return MallWeChatHolder.INSTANCE;
    }

    private MallWeChat(){
        final Activity activity = Mall.getConfiguration(ConfigKeys.CONTEXT);
        IWXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        IWXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getIWXAPI(){
        return IWXAPI;
    }

    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        IWXAPI.sendReq(req);
    }
}
