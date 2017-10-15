package com.ruolan.cmshopping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.net.callback.IError;
import com.ruolan.cainiao_core.net.callback.IFailure;
import com.ruolan.cainiao_core.net.callback.IRequest;
import com.ruolan.cainiao_core.net.callback.ISuccess;


/**
 * Created by wuyinlei on 2017/10/12.
 */

public class ExampleDelegate extends CainiaoDelegate {

    @Override
    public Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestCliect();
        Log.d("ExampleDelegate", "onBindView  Thread.currentThread():" + Thread.currentThread());
    }

    private void testRestCliect() {
        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(_mActivity)
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {
                        Log.d("ExampleDelegate", "IRequest  Thread.currentThread():" + Thread.currentThread());

                    }

                    @Override
                    public void onRequestEnd() {
                        Log.d("ExampleDelegate", "IRequest  Thread.currentThread():" + Thread.currentThread());

                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("ExampleDelegate", "onSuccess  Thread.currentThread():" + Thread.currentThread());
                        Toast.makeText(_mActivity, response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.d("ExampleDelegate", "IFailure  Thread.currentThread():" + Thread.currentThread());

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.d("ExampleDelegate", "Thread.currentThread():" + Thread.currentThread());
                    }
                }).build().get();
    }
}
