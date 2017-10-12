package com.ruolan.cmshopping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ruolan.cainiao_core.delegate.CainiaoDelegate;

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
}
