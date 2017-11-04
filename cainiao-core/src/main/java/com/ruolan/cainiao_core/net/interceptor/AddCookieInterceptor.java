package com.ruolan.cainiao_core.net.interceptor;

import com.ruolan.cainiao_core.util.storage.CainiaoPreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wuyinlei on 2017/11/4.
 *
 * @function
 */

public class AddCookieInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
        Observable.just(CainiaoPreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String cookie) throws Exception {
                        //给原生API请求附带上WEBVIEW拦截下来的Cookie
                        builder.addHeader("Cookie",cookie);

                    }
                });

        return chain.proceed(builder.build());
    }
}
