package com.hao.gomall_core.app;

import com.hao.gomall_core.utils.MallPreference;

public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

//    判断用户登陆状态
    public static void setSignState(boolean state){
        MallPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn(){
        return MallPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }
}
