package com.ruolan.cainiao_ec.delegate.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cainiao.cainiao_ui.ui.recycler.MultipleItemEntity;
import com.cainiao.cainiao_ui.ui.recycler.MultipleRecyclerAdapter;
import com.cainiao.cainiao_ui.ui.recycler.MultipleViewHolder;
import com.joanzapata.iconify.widget.IconTextView;
import com.ruolan.cainiao_core.app.Cainiao;
import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.net.callback.ISuccess;
import com.ruolan.cainiao_ec.R;

import java.util.List;

/**
 * Created by wuyinlei on 2017/11/5.
 *
 * @function
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {


    private boolean mIsSelectedAll = false;
    private double mTotalPrice = 0.00;
    private ICartItemListener mCartItemListener = null;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);

        //初始化总价
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(ShopItemType.SHOP_CART_PRICE);
            final int count = entity.getField(ShopItemType.SHOP_CART_COUNT);
            final double total = price * count;
            mTotalPrice = mTotalPrice + total;
        }

        //添加购物侧滑item布局
        addItemType(ShopItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    public void setCartItemListener(ICartItemListener listener) {
        this.mCartItemListener = listener;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }


    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);


        switch (holder.getItemViewType()) {
            case ShopItemType.SHOP_CART_ITEM:

                //先取出所有值
                final int id = entity.getField(ShopItemType.SHOP_CART_ID);
                final String thumb = entity.getField(ShopItemType.SHOP_CART_THUMB);
                final String title = entity.getField(ShopItemType.SHOP_CART_TITLE);
                final String desc = entity.getField(ShopItemType.SHOP_CART_DESC);
                final int count = entity.getField(ShopItemType.SHOP_CART_COUNT);
                final double price = entity.getField(ShopItemType.SHOP_CART_PRICE);


                //取出所以控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);

                //在左侧勾勾渲染之前改变全选与否状态
                entity.setField(ShopItemType.IS_SELECTED, mIsSelectedAll);

                final boolean isSelected = entity.getField(ShopItemType.IS_SELECTED);

                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imgThumb);

                //根据数据状态显示左侧的是否选中
                if (isSelected) {
                    iconIsSelected.setTextColor(
                            ContextCompat.getColor(
                                    Cainiao.getApplicationContext(),
                                    R.color.app_main)
                    );
                } else {
                    iconIsSelected.setTextColor(
                            Color.GRAY
                    );
                }

                //添加左侧的勾勾的点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final boolean currentSelected = entity.getField(ShopItemType.IS_SELECTED);

                        if (currentSelected) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopItemType.
                                    IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor(
                                    ContextCompat.getColor(
                                            Cainiao.getApplicationContext(),
                                            R.color.app_main));
                            entity.setField(ShopItemType.IS_SELECTED, true);
                        }
                    }
                });


                //添加加减事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopItemType.SHOP_CART_COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            RestClient.builder()
                                    .url("59e57875f757730a12fd0752/test/add/shop/cart")
                                    .loader(mContext)
                                    .params("count", currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum--;
                                            tvCount.setText(String.valueOf(countNum));
                                            if (mCartItemListener != null) {
                                                mTotalPrice = mTotalPrice - price;
                                                final double itemTotal = countNum * price;
                                                mCartItemListener.onItemClick(itemTotal);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });

                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopItemType.SHOP_CART_COUNT);
                        RestClient.builder()
                                .url("59e57875f757730a12fd0752/test/add/shop/cart")
                                .loader(mContext)
                                .params("count", currentCount)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        int countNum = Integer.parseInt(tvCount.getText().toString());
                                        countNum++;
                                        tvCount.setText(String.valueOf(countNum));
                                        if (mCartItemListener != null) {
                                            mTotalPrice = mTotalPrice + price;
                                            final double itemTotal = countNum * price;
                                            mCartItemListener.onItemClick(itemTotal);
                                        }
                                    }
                                })
                                .build()
                                .post();
                    }
                });



                break;


            default:

                break;
        }
    }

    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }
}
