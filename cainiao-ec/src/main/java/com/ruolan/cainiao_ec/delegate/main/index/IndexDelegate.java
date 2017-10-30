package com.ruolan.cainiao_ec.delegate.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cainiao.cainiao_ui.ui.recycler.BaseDecoration;
import com.cainiao.cainiao_ui.ui.refresh.RefreshHandler;
import com.joanzapata.iconify.widget.IconTextView;
import com.ruolan.cainiao_core.delegate.BaseDelegate;
import com.ruolan.cainiao_core.delegate.bottom.BottomItemDelegate;
import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.net.callback.ISuccess;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;
import com.ruolan.cainiao_ec.delegate.main.EcBottomDelegate;

import butterknife.BindView;

/**
 * Created by wuyinlei on 2017/10/16.
 *
 * @function 首页数据
 */

public class IndexDelegate extends BottomItemDelegate {


    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_purple
        );
        mRefreshLayout.setProgressViewOffset(true, 100, 250);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("59e57875f757730a12fd0752/test/index");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    private void initRecyclerView(){
        final GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(
                BaseDecoration.create(ContextCompat.getColor(getContext(),
                        R.color.app_background),5));

        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout,mRecyclerView,new IndexDataConvert());
    }



}
