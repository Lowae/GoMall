package com.hao.gomall.mall.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall.mall.R2;
import com.hao.gomall.mall.main.MallBottomDelegate;
import com.hao.gomall.mall.util.Constants;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.net.RestClient;
import com.hao.gomall_core.net.callback.ISuccess;
import com.hao.gomall_core.utils.MallLogger;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInDelegate extends MallDelegate {


    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText editSignInEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText editSignInPassword;

    private ISignListenter mISignListenter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListenter){
            mISignListenter = (ISignListenter) activity;
        }
    }

    private boolean checkForm() {
        final String email = Objects.requireNonNull(editSignInEmail.getText()).toString();
        final String password = Objects.requireNonNull(editSignInPassword.getText()).toString();


        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignInEmail.setError("邮箱格式错误");
            isPass = false;

        } else {
            editSignInEmail.setError(null);
        }


        if (password.isEmpty() || password.length() < 6) {
            editSignInPassword.setError("密码至少6位");
            isPass = false;

        } else {
            editSignInPassword.setError(null);
        }

        return isPass;
    }

    @OnClick(R2.id.btn_sign_in)
    public void onClickSignIn(){
        if (checkForm()){
            RestClient.builder()
                    .url(Constants.LOGIN_URL)
                    .params("email", Objects.requireNonNull(editSignInEmail.getText()).toString())
                    .params("password", Objects.requireNonNull(editSignInPassword.getText()).toString())

                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            MallLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListenter);
                            MallLogger.d("SignInDelegate", response);
                            getSupportDelegate().startWithPop(new MallBottomDelegate());
                        }
                    })
                    .build()
                    .get();

        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    public void onClickWeChat(){

    }

    @OnClick(R2.id.tv_link_sign_up)
    public void onClickLinkSignUp(){
        getSupportDelegate().start(new SignUpDalegate());
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

}
