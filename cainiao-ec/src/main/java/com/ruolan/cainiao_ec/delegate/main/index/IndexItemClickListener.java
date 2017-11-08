package com.ruolan.cainiao_ec.delegate.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_ec.detail.GoodsDetailDelegate;

/**
 * Created by wuyinlei on 2017/10/25.
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final CainiaoDelegate DELEGATE;

    private IndexItemClickListener(CainiaoDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(CainiaoDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final GoodsDetailDelegate detailDelegate = GoodsDetailDelegate.create();
        DELEGATE.getSupportDelegate().start(detailDelegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
