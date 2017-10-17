package com.ruolan.cainiao_ec.delegate.main;

import android.graphics.Color;

import com.ruolan.cainiao_core.delegate.bottom.BaseBottomDelegate;
import com.ruolan.cainiao_core.delegate.bottom.BottomItemDelegate;
import com.ruolan.cainiao_core.delegate.bottom.BottomTabBean;
import com.ruolan.cainiao_core.delegate.bottom.ItemBuilder;
import com.ruolan.cainiao_ec.delegate.main.cart.ShopCartDelegate;
import com.ruolan.cainiao_ec.delegate.main.discovery.DiscoverDelegate;
import com.ruolan.cainiao_ec.delegate.main.index.IndexDelegate;
import com.ruolan.cainiao_ec.delegate.main.personal.PersonalDelegate;
import com.ruolan.cainiao_ec.delegate.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by wuyinlei on 2017/10/16.
 */

public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
