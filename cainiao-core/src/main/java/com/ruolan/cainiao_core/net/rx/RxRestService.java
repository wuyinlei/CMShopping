package com.ruolan.cainiao_core.net.rx;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by wuyinlei on 2017/10/15.
 */

public interface RxRestService {

    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String,Object> params);

    @POST
    @FormUrlEncoded
    Observable<String> post(@Url String url, @FieldMap Map<String,Object> params);

    @POST
    @FormUrlEncoded
    Observable<String> postRaw(@Url String url, @Body RequestBody body);

    @PUT
    @FormUrlEncoded
    Observable<String> put(@Url String url, @FieldMap Map<String,Object> params);

    @PUT
    @FormUrlEncoded
    Observable<String> putRaw(@Url String url, @Body RequestBody body);


    @DELETE
    Observable<String> delete(@Url String url, @QueryMap Map<String,Object> params);


    @Streaming  //避免一次下载更多造成内存溢出   这个注解是为了下载一点写入一点
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap Map<String,Object> params);


    @POST
    @Multipart
    Observable<String> upload(@Url String url, @Part MultipartBody.Part files);



}
