package com.ruolan.cainiao_ec.delegate.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cainiao.cainiao_ui.ui.recycler.ItemType;
import com.cainiao.cainiao_ui.ui.recycler.MultipleFields;
import com.cainiao.cainiao_ui.ui.recycler.MultipleItemEntity;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by wuyinlei on 2017/11/13.
 *
 * @function
 */

public class ImageDelegate extends CainiaoDelegate {

    @BindView(R2.id.rv_image_container)
    RecyclerView mRecyclerView = null;

    private static final String ARG_PICTURES = "ARG_PICTURES";

    @Override
    public Object setLayout() {
        return R.layout.delegate_image;
    }

    private void initImages() {
        final ArrayList<String> pictures =
                getArguments().getStringArrayList(ARG_PICTURES);
        final ArrayList<MultipleItemEntity> entities = new ArrayList<>();
        final int size;
        if (pictures != null) {
            size = pictures.size();
            for (int i = 0; i < size; i++) {
                final String imageUrl = pictures.get(i);
                final MultipleItemEntity entity = MultipleItemEntity
                        .builder()
                        .setItemType(ItemType.SINGLE_BIG_IMAGE)
                        .setField(MultipleFields.IMAGE_URL, imageUrl)
                        .build();
                entities.add(entity);
            }
            final RecyclerImageAdapter adapter = new RecyclerImageAdapter(entities);
            mRecyclerView.setAdapter(adapter);
        }
    }

    public static ImageDelegate create(ArrayList<String> pictures) {
        final Bundle args = new Bundle();
        args.putStringArrayList(ARG_PICTURES, pictures);
        final ImageDelegate delegate = new ImageDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        initImages();
    }
}
