package com.ruolan.cainiao_ec.delegate.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cainiao.cainiao_ui.ui.recycler.DataConverter;
import com.cainiao.cainiao_ui.ui.recycler.MultipleFields;
import com.cainiao.cainiao_ui.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by wuyinlei on 2017/11/5.
 *
 * @function
 */

public class ShopCartDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = dataArray.size();

        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final String desc = data.getString("desc");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final int count = data.getInteger("count");

            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE,ShopItemType.SHOP_CART_ITEM)
                    .setField(ShopItemType.SHOP_CART_ID, id)
                    .setField(ShopItemType.SHOP_CART_COUNT,count)
                    .setField(ShopItemType.SHOP_CART_DESC,desc)
                    .setField(ShopItemType.SHOP_CART_PRICE,price)
                    .setField(ShopItemType.SHOP_CART_THUMB,thumb)
                    .setField(ShopItemType.SHOP_CART_TITLE,title)
                    .build();

            dataList.add(entity);
        }


        return dataList;
    }
}
