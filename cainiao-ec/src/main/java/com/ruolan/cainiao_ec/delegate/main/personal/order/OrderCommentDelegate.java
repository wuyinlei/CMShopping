package com.ruolan.cainiao_ec.delegate.main.personal.order;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_ec.R;

/**
 * Created by wuyinlei on 2017/11/6.
 *
 * @function
 */

@SuppressLint("ValidFragment")
public class OrderCommentDelegate extends CainiaoDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
