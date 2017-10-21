package com.ruolan.cainiao_core.wechat.templete;

import com.ruolan.cainiao_core.wechat.BaseWXEntryActivity;
import com.ruolan.cainiao_core.wechat.CainiaoWeChat;

/**
 * Created by wuyinlei on 2017/10/18.
 */

public class WXEntryTemplete extends BaseWXEntryActivity {


    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        CainiaoWeChat.geiInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
