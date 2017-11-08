package com.ruolan.cainiao_ec.delegate.main.personal.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cainiao.cainiao_ui.ui.recycler.MultipleItemEntity;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.net.callback.ISuccess;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wuyinlei on 2017/11/6.
 *
 * @function
 */

public class AddressDelegate extends CainiaoDelegate implements ISuccess {

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        RestClient.builder()
                .url("59e57875f757730a12fd0752/test/address")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new AddressDataConverter().setJsonData(response).convert();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }
}
