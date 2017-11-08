package com.ruolan.cainiao_ec.delegate.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.cainiao.cainiao_ui.ui.recycler.MultipleItemEntity;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.net.callback.ISuccess;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;
import com.ruolan.cainiao_ec.delegate.main.sort.SortDelegate;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wuyinlei on 2017/10/30.
 *
 * @function 分类左侧fragment
 */

public class VerticalListDelegate extends CainiaoDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView;

    private void initRecyclerView(){
        final LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("59e57875f757730a12fd0752/test/sort/list")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        if (!TextUtils.isEmpty(response)) {
                            final List<MultipleItemEntity> data =
                                    new VerticalListDataConverter().setJsonData(response).convert();

                            final SortDelegate delegate = getParentDelegate();
                            final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data,delegate);
                            mRecyclerView.setAdapter(adapter);
                        }
                    }
                }).build().get();
    }
}
