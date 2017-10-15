package com.ruolan.cainiao_core.net.interceptor;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wuyinlei on 2017/10/15.
 *
 * @function 基类Interceptor
 */

public abstract class BaseInterceptor implements Interceptor {

    /**
     * 获取请求参数
     *
     * @param chain Chain
     * @return LinkedHashMap
     */
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {

        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    /**
     * 获取请求参数
     *
     * @param chain CHAIN
     * @param key   KEY
     * @return LinkedHashMap
     */
    protected String getUrlParameters(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    /**
     * 获取body里面的参数
     *
     * @param chain Chain
     * @return LinkedHashMap
     */
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = formBody.size();
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;
    }

    /**
     * 获取body里面的参数
     *
     * @param chain Chain
     * @param key   key
     * @return LinkedHashMap
     */
    protected String getBodyParameters(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }
}
