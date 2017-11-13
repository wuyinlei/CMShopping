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
import android.widget.Toast;

import com.cainiao.cainiao_ui.ui.recycler.BaseDecoration;
import com.cainiao.cainiao_ui.ui.refresh.RefreshHandler;
import com.joanzapata.iconify.widget.IconTextView;
import com.ruolan.cainiao_core.delegate.bottom.BottomItemDelegate;
import com.ruolan.cainiao_core.util.callback.CallbackManager;
import com.ruolan.cainiao_core.util.callback.CallbackType;
import com.ruolan.cainiao_core.util.callback.IGlobalCallback;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;
import com.ruolan.cainiao_ec.delegate.main.EcBottomDelegate;
import com.ruolan.cainiao_ec.delegate.main.index.search.SearchDelegate;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wuyinlei on 2017/10/16.
 *
 * @function 首页数据
 */

public class IndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener {


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

    @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode() {
        startScanWithCheck(this.getParentDelegate());
    }

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

        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
                    @Override
                    public void executeCallback(@Nullable String args) {
                        Toast.makeText(getContext(), "得到的二维码是" + args, Toast.LENGTH_LONG).show();
                    }
                });

        mSearchView.setOnFocusChangeListener(this);

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            getParentDelegate().getSupportDelegate().start(new SearchDelegate());
        }
    }
}
