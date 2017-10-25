package com.cainiao.cainiao_ui.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cainiao.cainiao_ui.ui.recycler.DataConverter;
import com.cainiao.cainiao_ui.ui.recycler.MultipleRecyclerAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ruolan.cainiao_core.app.Cainiao;
import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.net.callback.IError;
import com.ruolan.cainiao_core.net.callback.IFailure;
import com.ruolan.cainiao_core.net.callback.ISuccess;

/**
 * Created by wuyinlei on 2017/10/22.
 *
 * @function 刷新处理
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {


    private final SwipeRefreshLayout REFRESH_LAYOUT;

    private final PageBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                          RecyclerView recyclerView,
                          DataConverter converter,
                          PageBean bean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.BEAN = bean;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter converter){

        return new RefreshHandler(swipeRefreshLayout,recyclerView,converter,new PageBean());
    }

//    public RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT) {
//        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
//
//    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Cainiao.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));

                        //设置adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
//                        Toast.makeText(Cainiao.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                }).failure(new IFailure() {
            @Override
            public void onFailure() {

            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {

            }
        }).build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
