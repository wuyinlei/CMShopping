package com.ruolan.cainiao_ec.delegate.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.cainiao.cainiao_ui.ui.recycler.MultipleItemEntity;
import com.joanzapata.iconify.widget.IconTextView;
import com.ruolan.cainiao_core.delegate.bottom.BottomItemDelegate;
import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.net.callback.ISuccess;
import com.ruolan.cainiao_core.util.log.CainiaoLogger;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;
import com.ruolan.cainiao_ec.delegate.main.pay.FastPay;
import com.ruolan.cainiao_ec.delegate.main.pay.IAlPayResultListener;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wuyinlei on 2017/10/16.
 *
 * @function
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, IAlPayResultListener, ICartItemListener {


    private ShopCartAdapter mAdapter = null;
    //购物车数量标记
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;

    //用来判断ViewStubCompat是否已经初始化过了
    private boolean isHasInflate = false;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;


    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    @SuppressWarnings("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            if (!isHasInflate) {
                final View stubView = mStubNoItem.inflate();
                final AppCompatTextView tvToBuy =
                        (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
                tvToBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "你该购物啦！", Toast.LENGTH_SHORT).show();
                    }
                });
                isHasInflate = true;
            }
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopItemType.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }

        int size = deleteEntities.size();
        for (int i = 0; i < size; i++) {
            int dataCount = data.size();
            int currentPosition = deleteEntities.get(i).getField(ShopItemType.SHOP_CART_POSITION);
            if (currentPosition < dataCount) {
                mAdapter.remove(currentPosition);

                //用于修复购物车删除购物商品逻辑的错乱问题
                for (; currentPosition < dataCount - 1; currentPosition++) {
                    int rawItemPos = data.get(currentPosition).getField(ShopItemType.SHOP_CART_POSITION);
                    data.get(currentPosition).setField(ShopItemType.SHOP_CART_POSITION, --rawItemPos);
                }
            }

        }

//        for (MultipleItemEntity entity : deleteEntities) {
//            int removePosition;
//            final int entityPosition = entity.getField(ShopItemType.SHOP_CART_POSITION);
//            if (entityPosition > mCurrentCount - 1) {
//                removePosition = entityPosition - mCurrentCount;
//            } else {
//                removePosition = entityPosition - 1;
//            }
//            if (removePosition <= mAdapter.getItemCount()) {
//                mAdapter.remove(removePosition);
//                mCurrentCount = mAdapter.getItemCount();
//                //更新数据
//                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
//            }
//        }

        checkItemCount();
    }

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0) {
            mIconSelectAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);
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

    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay() {
        createOrder();
    }

    //创建订单，注意，和支付是没有关系的
    private void createOrder() {
        final String orderUrl = "http://app.api.zanzuanshi.com/api/v1/peyment";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        orderParams.put("userid", 264392);
        orderParams.put("amount", 0.01);
        orderParams.put("comment", "测试支付");
        orderParams.put("type", 1);
        orderParams.put("ordertype", 0);
        orderParams.put("isanonymous", true);
        orderParams.put("followeduser", 0);
        RestClient.builder()
                .url(orderUrl)
                .loader(getContext())
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //进行具体的支付
                        CainiaoLogger.d("ORDER", response);
                        final int orderId = JSON.parseObject(response).getInteger("result");
                        FastPay.create(ShopCartDelegate.this)
                                .setPayResultListener(ShopCartDelegate.this)
                                .setOrderId(orderId)
                                .beginPayDialog();
                    }
                })
                .build()
                .post();

    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }




    @Override
    public void onSuccess(String response) {

        ArrayList<MultipleItemEntity> data =
                new ShopCartDataConverter().setJsonData(response).convert();
        mAdapter = new ShopCartAdapter(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
        mAdapter.setCartItemListener(this);
        checkItemCount();

    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}
