package com.ruolan.cainiao_core.net;

import android.content.Context;

import com.ruolan.cainiao_core.net.callback.IError;
import com.ruolan.cainiao_core.net.callback.IFailure;
import com.ruolan.cainiao_core.net.callback.IRequest;
import com.ruolan.cainiao_core.net.callback.ISuccess;
import com.ruolan.cainiao_core.net.callback.RequestCallback;
import com.ruolan.cainiao_core.ui.CainiaoLoader;
import com.ruolan.cainiao_core.ui.LoaderStyle;

import java.lang.ref.PhantomReference;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by wuyinlei on 2017/10/15.
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreate.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String URL,
                      WeakHashMap<String, Object> params, IRequest REQUEST,
                      ISuccess SUCCESS,
                      IError ERROR,
                      IFailure FAILURE,
                      RequestBody BODY,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = URL;
        PARAMS.putAll(params);
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.BODY = BODY;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService restService = RestCreate.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null){
            CainiaoLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = restService.get(URL, PARAMS);
                break;

            case POST:
                call = restService.post(URL, PARAMS);
                break;

            case PUT:
                call = restService.put(URL, PARAMS);
                break;

            case DELETE:
                call = restService.delete(URL, PARAMS);
                break;

            default:

                break;

        }


        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }

    private Callback<String> getRequestCallback() {
        return new RequestCallback(REQUEST, SUCCESS, ERROR, FAILURE,LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
