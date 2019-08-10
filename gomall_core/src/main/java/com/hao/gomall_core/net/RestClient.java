package com.hao.gomall_core.net;

import android.content.Context;

import com.hao.gomall_core.app.Configurator;
import com.hao.gomall_core.app.Mall;
import com.hao.gomall_core.net.callback.IError;
import com.hao.gomall_core.net.callback.IFailure;
import com.hao.gomall_core.net.callback.IRequest;
import com.hao.gomall_core.net.callback.ISuccess;
import com.hao.gomall_core.net.callback.RequestCallBacks;
import com.hao.gomall_core.ui.LoaderStyle;
import com.hao.gomall_core.ui.MallLoader;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private Context context;


    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest irequest,
                      ISuccess isuccess,
                      IFailure ifailure,
                      IError ierror,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context) {
        URL = url;
        PARAMS.putAll(params);
        IREQUEST = irequest;
        ISUCCESS = isuccess;
        IFAILURE = ifailure;
        IERROR = ierror;
        BODY = body;
        LOADER_STYLE = loaderStyle;
        this.context = context;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        Configurator.getInstance().withApiHost(URL);
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (IREQUEST != null){
            IREQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null){
            MallLoader.showLoading(context, LOADER_STYLE);
        }
        switch (method){
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null){
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallBacks(IREQUEST, ISUCCESS, IFAILURE, IERROR, LOADER_STYLE);
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

}
