package com.ruolan.cainiao_core.net;

import java.util.Map;
import java.util.Objects;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by wuyinlei on 2017/10/15.
 */

public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String,Object> params);

    @POST
    @FormUrlEncoded
    Call<String> post(@Url String url, @FieldMap Map<String,Object> params);


    @PUT
    @FormUrlEncoded
    Call<String> put(@Url String url, @FieldMap Map<String,Object> params);


    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String,Object> params);


    @Streaming  //避免一次下载更多造成内存溢出   这个注解是为了下载一点写入一点
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String,Object> params);


    @POST
    @Multipart
    Call<String> upload(@Url String url, @Part MultipartBody.Part files);

}
