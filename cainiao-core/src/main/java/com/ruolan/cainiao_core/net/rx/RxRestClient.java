package com.ruolan.cainiao_core.net.rx;

import android.content.Context;
import android.hardware.Camera;

import com.ruolan.cainiao_core.net.HttpMethod;
import com.ruolan.cainiao_core.net.RestClientBuilder;
import com.ruolan.cainiao_core.net.RestCreate;
import com.ruolan.cainiao_core.net.RestService;
import com.ruolan.cainiao_core.net.callback.IError;
import com.ruolan.cainiao_core.net.callback.IFailure;
import com.ruolan.cainiao_core.net.callback.IRequest;
import com.ruolan.cainiao_core.net.callback.ISuccess;
import com.ruolan.cainiao_core.net.callback.RequestCallback;
import com.ruolan.cainiao_core.net.download.DownloadHandler;
import com.ruolan.cainiao_core.ui.CainiaoLoader;
import com.ruolan.cainiao_core.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by wuyinlei on 2017/10/15.
 */

public class RxRestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreate.getParams();
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;

    //下载参数
    public RxRestClient(String URL,
                        WeakHashMap<String, Object> params,
                        RequestBody BODY,
                        File file,
                        Context context,
                        LoaderStyle loaderStyle) {
        this.URL = URL;
        PARAMS.putAll(params);
        this.BODY = BODY;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {
        final RxRestService restService = RestCreate.getRxRestService();
//        Call<String> call = null;

        Observable<String> observable = null;

        if (LOADER_STYLE != null) {
            CainiaoLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                observable = restService.get(URL, PARAMS);
                break;

            case POST:
                observable = restService.post(URL, PARAMS);
                break;

            case POST_RAW:
                observable = restService.postRaw(URL, BODY);
                break;


            case PUT:
                observable = restService.put(URL, PARAMS);
                break;

            case PUT_RAW:
                observable = restService.postRaw(URL, BODY);
                break;

            case DELETE:
                observable = restService.delete(URL, PARAMS);
                break;

            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = restService.upload(URL, body);
                break;

            default:

                break;

        }


        if (observable != null) {
            return observable;
        } else {
            return null;
        }

    }


    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.POST_RAW);
        }
//
    }

    public final Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable<String> delete() {
        return
                request(HttpMethod.DELETE);
    }


    public final Observable<ResponseBody> download() {
        return RestCreate.getRxRestService().download(URL, PARAMS);
    }

}
