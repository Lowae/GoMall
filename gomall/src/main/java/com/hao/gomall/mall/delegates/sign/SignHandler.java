package com.hao.gomall.mall.delegates.sign;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hao.gomall.mall.db.DBManager;
import com.hao.gomall.mall.db.UserProfile;
import com.hao.gomall_core.app.AccountManager;

public class SignHandler {

    public static void onSignUp(String response, ISignListenter signListenter){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DBManager.getInstance().getDao().insert(profile);

        AccountManager.setSignState(true);
        signListenter.onSignUpSuccess();
    }

    public static void onSignIn(String response, ISignListenter signListenter){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DBManager.getInstance().getDao().insert(profile);

        AccountManager.setSignState(true);
        signListenter.onSignInSuccess();
    }

}
