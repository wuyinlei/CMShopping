package com.ruolan.cainiao_core.net.interceptor;

import android.support.annotation.RawRes;
import android.util.Log;

import com.ruolan.cainiao_core.util.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by wuyinlei on 2017/10/15.
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int debugRawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = debugRawId;
    }

    /**
     * 获取实例返回体
     *
     * @param chain Chain
     * @param json  json文件
     * @return Response
     */
    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        final String url = chain.request().url().toString();
        Log.d("DebugInterceptor", url);
        Log.d("DebugInterceptor", DEBUG_URL);
        if (url.contains(DEBUG_URL)){
            Log.d("DebugInterceptor", "到这了");
            return debugResponse(chain,DEBUG_RAW_ID);
        }

        return chain.proceed(chain.request());
    }
}
