package com.ruolan.cainiao_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.ruolan.cainiao_core.R;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by wuyinlei on 2017/10/12.
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract CainiaoDelegate setRootDelegate();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.dategate_container);
        setContentView(container);
        if (savedInstanceState == null){
            //第一次加载
            loadRootFragment(R.id.dategate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
