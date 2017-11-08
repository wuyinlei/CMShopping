package com.ruolan.cainiao_ec.delegate.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ruolan.cainiao_core.delegate.bottom.BottomItemDelegate;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.delegate.main.sort.content.ContentDelegate;
import com.ruolan.cainiao_ec.delegate.main.sort.list.VerticalListDelegate;

/**
 * Created by wuyinlei on 2017/10/16.
 *
 * @function 分类fragment
 */

public class SortDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container,listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
