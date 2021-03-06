package com.ruolan.cainiao_core.net;

import android.content.Context;
import android.content.pm.ProviderInfo;

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

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
    private final File FILE;

    //下载参数
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public RestClient(String URL,
                      WeakHashMap<String, Object> params, IRequest REQUEST,
                      ISuccess SUCCESS,
                      IError ERROR,
                      IFailure FAILURE,
                      RequestBody BODY,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle,
                      String downloadDir,
                      String extension,
                      String name) {
        this.URL = URL;
        PARAMS.putAll(params);
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.BODY = BODY;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
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

            case POST_RAW:
                call = restService.postRaw(URL, BODY);
                break;


            case PUT:
                call = restService.put(URL, PARAMS);
                break;

            case PUT_RAW:
                call = restService.postRaw(URL, BODY);
                break;

            case DELETE:
                call = restService.delete(URL, PARAMS);
                break;

            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = restService.upload(URL, body);
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
        if (BODY == null){
            request(HttpMethod.POST);
        } else {
            if (PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
//
    }

    public final void upload(){
        if (BODY == null){
            request(HttpMethod.UPLOAD);
        }else {
            if (PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }


    public final void download(){
        new DownloadHandler(URL,REQUEST,SUCCESS,ERROR,FAILURE,DOWNLOAD_DIR,EXTENSION,NAME).handlerDownload();
    }

}
