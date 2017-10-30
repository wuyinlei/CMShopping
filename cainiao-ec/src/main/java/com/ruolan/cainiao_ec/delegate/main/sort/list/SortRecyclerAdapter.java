package com.ruolan.cainiao_ec.delegate.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.cainiao.cainiao_ui.ui.recycler.ItemType;
import com.cainiao.cainiao_ui.ui.recycler.MultipleFields;
import com.cainiao.cainiao_ui.ui.recycler.MultipleItemEntity;
import com.cainiao.cainiao_ui.ui.recycler.MultipleRecyclerAdapter;
import com.cainiao.cainiao_ui.ui.recycler.MultipleViewHolder;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.delegate.main.sort.SortDelegate;

import java.util.List;

/**
 * Created by wuyinlei on 2017/10/30.
 *
 * @function 分类左侧的adapter
 */

public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        addItemType(ItemType.VERTICAL_MUNE_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case ItemType.VERTICAL_MUNE_LIST:
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClicked = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line_left = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition){
//                            mPrePosition = currentPosition;
                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG,false);
                            notifyItemChanged(mPrePosition);
                            //更新选中的item的状态
                            entity.setField(MultipleFields.TAG,true);
                            notifyItemChanged(currentPosition);

                            mPrePosition = currentPosition;

                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                        }

                    }
                });

                if (isClicked){
                    line_left.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.app_main));
                    line_left.setBackgroundColor(ContextCompat.getColor(mContext,R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                } else {
                    line_left.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.item_background));
                }

                name.setText(text);

                break;

            default:

                break;
        }
    }
}
