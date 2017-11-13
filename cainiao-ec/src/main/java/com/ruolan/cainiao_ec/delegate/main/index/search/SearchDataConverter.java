package com.ruolan.cainiao_ec.delegate.main.index.search;

import com.alibaba.fastjson.JSONArray;
import com.cainiao.cainiao_ui.ui.recycler.DataConverter;
import com.cainiao.cainiao_ui.ui.recycler.MultipleFields;
import com.cainiao.cainiao_ui.ui.recycler.MultipleItemEntity;
import com.ruolan.cainiao_core.util.storage.CainiaoPreference;

import java.util.ArrayList;

/**
 * Created by wuyinlei on 2017/11/13.
 */

public class SearchDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr =
                CainiaoPreference.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if (!jsonStr.equals("")) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }

        return ENTITIES;
    }
}
