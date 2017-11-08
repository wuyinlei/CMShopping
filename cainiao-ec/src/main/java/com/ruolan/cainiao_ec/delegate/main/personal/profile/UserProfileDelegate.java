package com.ruolan.cainiao_ec.delegate.main.personal.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;
import com.ruolan.cainiao_ec.delegate.main.personal.list.ListAdapter;
import com.ruolan.cainiao_ec.delegate.main.personal.list.ListBean;
import com.ruolan.cainiao_ec.delegate.main.personal.list.ListItemType;
import com.ruolan.cainiao_ec.delegate.main.personal.setting.NameDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wuyinlei on 2017/11/6.
 *
 * @function
 */

public class UserProfileDelegate extends CainiaoDelegate {


    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        final ListBean image = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("http://img4.3lian.com/sucai/img3/459/086.gif")
                .build();

        final ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("姓名")
                .setDelegate(new NameDelegate())
                .setValue("未设置姓名")
                .build();

        final ListBean gender = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置性别")
                .build();

        final ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("未设置生日")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new UserProfileClickListener(this));

    }
}
