package com.ruolan.cainiao_core.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ruolan.cainiao_core.app.Cainiao;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by wuyinlei on 2017/10/18.
 */

public abstract class BaseWXActivity extends AppCompatActivity implements IWXAPIEventHandler{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //必须卸载onCreate()中

        CainiaoWeChat.geiInstance().getIWXAPI().handleIntent(getIntent(),this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        CainiaoWeChat.geiInstance().getIWXAPI().handleIntent(getIntent(),this);

    }
}
