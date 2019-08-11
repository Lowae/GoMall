package com.hao.gomall_core.net.interceptors;

import android.support.annotation.RawRes;

import com.hao.gomall_core.utils.FileUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debug_url, int debug_raw_id) {
        DEBUG_URL = debug_url;
        DEBUG_RAW_ID = debug_raw_id;
    }

    private Response getResponse(Chain chain, String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId){
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)){
            return debugResponse(chain, DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
