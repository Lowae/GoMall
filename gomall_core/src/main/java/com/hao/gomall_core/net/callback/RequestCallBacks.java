package com.hao.gomall_core.net.callback;

import android.os.Handler;

import com.hao.gomall_core.ui.LoaderStyle;
import com.hao.gomall_core.ui.MallLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallBacks implements Callback<String> {

    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();

    public RequestCallBacks(IRequest irequest, ISuccess isuccess, IFailure ifailure, IError ierror, LoaderStyle style) {
        IREQUEST = irequest;
        ISUCCESS = isuccess;
        IFAILURE = ifailure;
        IERROR = ierror;
        LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (ISUCCESS != null){
                    ISUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if (IERROR != null){
                IERROR.onError(response.code(), response.message());
            }
        }

        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (IFAILURE != null){
            IFAILURE.onFailure();
        }
        if (IREQUEST != null){
            IREQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading(){
        if (LOADER_STYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MallLoader.stopLoading();

                }
            }, 2000);
        }
    }
}
