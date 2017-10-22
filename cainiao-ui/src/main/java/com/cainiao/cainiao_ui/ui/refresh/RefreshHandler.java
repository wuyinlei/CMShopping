package com.cainiao.cainiao_ui.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

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

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {


    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

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
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Cainiao.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
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
}
