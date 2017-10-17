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
import com.ruolan.cainiao_core.net.rx.RxRestClient;


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

    }

    private void testRestCliect() {



    }
}
