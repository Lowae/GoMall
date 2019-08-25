package com.hao.gomall.mall.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall.mall.R2;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.net.RestClient;
import com.hao.gomall_core.net.callback.ISuccess;
import com.hao.gomall_core.utils.MallLogger;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpDalegate extends MallDelegate {


    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText editSignUpName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText editSignUpEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText editSignUpPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText editSignUpPassword;
    @BindView(R2.id.edit_sign_up_confirm_password)
    TextInputEditText editSignUpConfirmPassword;
    @BindView(R2.id.btn_sign_up)
    AppCompatButton btnSignUp;
    @BindView(R2.id.tv_link_sign_in)
    AppCompatTextView tvLinkSignIn;

    private ISignListenter mSignListenter;



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListenter){
            mSignListenter = (ISignListenter) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    @OnClick(R2.id.btn_sign_up)
    public void onClickSignUp(){
        if (checkForm()){
            RestClient.builder()
                    .url("http://169.254.7.30:8080/gomall/user_profile.json")
                    .params("name", editSignUpName.getText().toString())
                    .params("email", editSignUpEmail.getText().toString())
                    .params("phone", editSignUpPhone.getText().toString())
                    .params("password", editSignUpPassword.getText().toString())

                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            MallLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response, mSignListenter);
                        }
                    })
                    .build()
                    .get();

        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    public void onClickLinkSignIn(){
        getSupportDelegate().start(new SignInDelegate(), SINGLETASK);
    }

    private boolean checkForm() {
        final String name = editSignUpName.getText().toString();
        final String email = editSignUpEmail.getText().toString();
        final String phone = editSignUpPhone.getText().toString();
        final String password = editSignUpPassword.getText().toString();
        final String confirmPassword = editSignUpConfirmPassword.getText().toString();

        Log.d("SignUp",name+""+email+""+phone+""+password+""+confirmPassword);

        boolean isPass = true;
        if (name.isEmpty()) {
            editSignUpName.setError("请输入用户名");
            isPass = false;
        } else {
            editSignUpName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignUpEmail.setError("邮箱格式错误");
            isPass = false;

        } else {
            editSignUpName.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            editSignUpPhone.setError("手机号码错误");
            isPass = false;

        } else {
            editSignUpName.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignUpPassword.setError("密码至少6位");
            isPass = false;

        } else {
            editSignUpName.setError(null);
        }

        if (confirmPassword.isEmpty() || confirmPassword.length() < 6 || !confirmPassword.equals(password)) {
            editSignUpConfirmPassword.setError("密码验证错误");
            isPass = false;

        } else {
            editSignUpName.setError(null);
        }

        return isPass;
    }
}
