package com.hao.gomall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.hao.gomall.mall.launcher.LauncherDelegate;
import com.hao.gomall_core.activities.ProxyActivity;
import com.hao.gomall_core.delegates.MallDelegate;

public class GoMallActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public MallDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
