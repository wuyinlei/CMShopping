package com.ruolan.cainiao_ec.delegate.main.index.search;

import android.support.v7.widget.AppCompatTextView;

import com.cainiao.cainiao_ui.ui.recycler.MultipleFields;
import com.cainiao.cainiao_ui.ui.recycler.MultipleItemEntity;
import com.cainiao.cainiao_ui.ui.recycler.MultipleRecyclerAdapter;
import com.cainiao.cainiao_ui.ui.recycler.MultipleViewHolder;
import com.ruolan.cainiao_ec.R;

import java.util.List;

/**
 * Created by wuyinlei on 2017/11/13.
 *
 * @function
 */

public class SearchAdapter extends MultipleRecyclerAdapter {

    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (entity.getItemType()) {
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;
        }
    }
}
