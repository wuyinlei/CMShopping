package com.ruolan.cainiao_ec.delegate.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.net.callback.ISuccess;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wuyinlei on 2017/10/30.
 *
 * @function 分类右侧的内容数据
 */

public class ContentDelegate extends CainiaoDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = new ArrayList<>();

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        Log.d("ContentDelegate", "contentId:" + contentId);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle != null) {
            mContentId = bundle.getInt(ARG_CONTENT_ID);
        }
    }

    private void initRecyclerView(){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        super.onBindView(savedInstanceState, rootView);
        initRecyclerView();
        initData();
    }

    private void initData(){
        RestClient.builder()
                .url("59e57875f757730a12fd0752/test/sort/content/data_one")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mData = new SectionDataConverter().convert(response);
                        final SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content,
                                R.layout.item_section_header,mData);

                        mRecyclerView.setAdapter(sectionAdapter);

                    }
                })
                .build()
                .get();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_content;
    }
}
