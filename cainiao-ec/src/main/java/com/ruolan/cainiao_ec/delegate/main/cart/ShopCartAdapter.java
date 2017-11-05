package com.ruolan.cainiao_ec.delegate.main.cart;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cainiao.cainiao_ui.ui.recycler.MultipleItemEntity;
import com.cainiao.cainiao_ui.ui.recycler.MultipleRecyclerAdapter;
import com.cainiao.cainiao_ui.ui.recycler.MultipleViewHolder;
import com.joanzapata.iconify.widget.IconTextView;
import com.ruolan.cainiao_ec.R;

import java.util.List;

/**
 * Created by wuyinlei on 2017/11/5.
 *
 * @function
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {


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

        //添加购物侧滑item布局
        addItemType(ShopItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
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

                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imgThumb);

                break;


            default:

                break;
        }
    }
}
