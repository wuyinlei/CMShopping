package com.ruolan.cainiao_ec.delegate.main.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ruolan.cainiao_core.delegate.bottom.BottomItemDelegate;
import com.ruolan.cainiao_core.delegate.web.WebDelegateImpl;
import com.ruolan.cainiao_ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by wuyinlei on 2017/10/16.
 *
 * @function 发现界面
 */

public class DiscoverDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(this.getParentDelegate());
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container,delegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
