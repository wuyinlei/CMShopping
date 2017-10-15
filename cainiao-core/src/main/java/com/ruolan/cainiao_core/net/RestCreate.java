package com.ruolan.cainiao_core.net;

import com.ruolan.cainiao_core.app.Cainiao;
import com.ruolan.cainiao_core.app.ConfigType;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by wuyinlei on 2017/10/15.
 */

public class RestCreate {

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class  RetrofitHolder{
        private static final String BASE_URL = (String) Cainiao.getConfitgurations().get(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }


    private static final class OKHttpHolder{

        private static final  int TIME_OUT = 60;

        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

    }

    private static final class RestServiceHolder{

        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);

    }

}
