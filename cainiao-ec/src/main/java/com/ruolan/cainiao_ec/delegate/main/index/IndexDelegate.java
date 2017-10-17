package com.ruolan.cainiao_ec.delegate.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ruolan.cainiao_core.delegate.bottom.BottomItemDelegate;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;

import butterknife.BindView;

/**
 * Created by wuyinlei on 2017/10/16.
 */

public class IndexDelegate extends BottomItemDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }
}
