package com.hao.gomall.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.hao.gomall.mall.launcher.LauncherDelegate;
import com.hao.gomall.mall.main.MallBottomDelegate;
import com.hao.gomall.mall.sign.ISignListenter;
import com.hao.gomall.mall.sign.SignInDelegate;
import com.hao.gomall_core.activities.ProxyActivity;
import com.hao.gomall_core.app.Mall;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.ui.launcher.ILauncherListener;
import com.hao.gomall_core.ui.launcher.OnLauncherFinishTag;

public class GoMallActivity extends ProxyActivity implements ISignListenter, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mall.getConfigurator().withContext(this);
    }


    @Override
    public MallDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
//                Toast.makeText(this, "启动结束，已登陆", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new MallBottomDelegate());
                break;
            case NOT_SIGNED:
//                Toast.makeText(this, "启动结束，未登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    public void post(Runnable runnable) {

    }
}
