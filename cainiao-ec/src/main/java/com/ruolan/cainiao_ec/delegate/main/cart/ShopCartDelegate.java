package com.ruolan.cainiao_ec.delegate.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.cainiao.cainiao_ui.ui.recycler.MultipleItemEntity;
import com.joanzapata.iconify.widget.IconTextView;
import com.ruolan.cainiao_core.delegate.bottom.BottomItemDelegate;
import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.net.callback.ISuccess;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by wuyinlei on 2017/10/16.
 *
 * @function
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess {

    private ShopCartAdapter mAdapter = null;
    //购物车数量标记
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;



    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("59e57875f757730a12fd0752/test/shop/cart/data")
                .success(this)
                .build()
                .get();

    }

    private void initRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    public void onSuccess(String response) {

        ArrayList<MultipleItemEntity> data =
                new ShopCartDataConverter().setJsonData(response).convert();


        ShopCartAdapter adapter = new ShopCartAdapter(data);

        mRecyclerView.setAdapter(adapter);

//        Toast.makeText(_mActivity, response, Toast.LENGTH_SHORT).show();
    }
}
