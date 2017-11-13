package com.ruolan.cainiao_ec.delegate.main.index;

import android.view.View;

import com.cainiao.cainiao_ui.ui.recycler.MultipleFields;
import com.cainiao.cainiao_ui.ui.recycler.MultipleItemEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_ec.delegate.detail.GoodsDetailDelegate;

/**
 * Created by wuyinlei on 2017/10/25.
 *
 * @function
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
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int goodsId = entity.getField(MultipleFields.ID);
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(delegate);
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
