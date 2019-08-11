package com.hao.gomall_core.net.rx;

import android.content.Context;

import com.hao.gomall_core.net.RestCreator;
import com.hao.gomall_core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RxRestClientBuilder {

    private String mUrl;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private RequestBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;
    private File mFlie;
    private String mDownloadDir;
    private String mExtension;
    private String mName;

    RxRestClientBuilder(){

    }

    public final RxRestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value){
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    public final RxRestClientBuilder loader(Context context, LoaderStyle style){
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClientBuilder file(File file){
        this.mFlie = file;
        return this;
    }

    public final RxRestClientBuilder file(String filePath){
        this.mFlie = new File(filePath);
        return this;
    }

    public final RxRestClientBuilder file(String key, Object value){
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RxRestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RxRestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RxRestClient build(){
        return new RxRestClient(mUrl, PARAMS, mBody, mLoaderStyle, mFlie, mDownloadDir, mExtension, mName, mContext);
    }
}
