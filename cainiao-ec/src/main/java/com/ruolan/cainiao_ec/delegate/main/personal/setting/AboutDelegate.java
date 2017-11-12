package com.ruolan.cainiao_ec.delegate.main.personal.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.net.callback.ISuccess;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;

import butterknife.BindView;

/**
 * Created by wuyinlei on 2017/11/12.
 *
 * @function 关于界面
 */

public class AboutDelegate extends CainiaoDelegate {


    @BindView(R2.id.tv_about)
    AppCompatTextView mTvAbout;

    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        RestClient.builder()
                .url("59e57875f757730a12fd0752/test/about")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String info = JSON.parseObject(response).getString("data");
                        mTvAbout.setText(info);
                    }
                })
                .build()
                .get();
    }
}
