package com.hao.gomall_core.net.download;

import android.os.AsyncTask;

import com.hao.gomall_core.net.RestCreator;
import com.hao.gomall_core.net.callback.IError;
import com.hao.gomall_core.net.callback.IFailure;
import com.hao.gomall_core.net.callback.IRequest;
import com.hao.gomall_core.net.callback.ISuccess;
import com.hao.gomall_core.utils.FileUtil;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadHandler {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownloadHandler(String url, IRequest request, String download_dir, String extension, String name, ISuccess success, IFailure failure, IError error) {
        URL = url;
        REQUEST = request;
        DOWNLOAD_DIR = download_dir;
        EXTENSION = extension;
        NAME = name;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
    }

    public final void handleDownload(){
        if (REQUEST != null){
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            final ResponseBody responseBody = response.body();
                            final DownloadTask downloadTask = new DownloadTask(REQUEST, SUCCESS);
                            downloadTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, responseBody, NAME);
                            if (downloadTask.isCancelled()){
                                if (REQUEST != null){
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else {
                            if (ERROR != null){
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null){
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
