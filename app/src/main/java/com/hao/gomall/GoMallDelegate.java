package com.hao.gomall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.net.RestClient;
import com.hao.gomall_core.net.callback.IError;
import com.hao.gomall_core.net.callback.IFailure;
import com.hao.gomall_core.net.callback.ISuccess;


public class GoMallDelegate extends MallDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_gomall;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://127.0.0.1/index/")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
