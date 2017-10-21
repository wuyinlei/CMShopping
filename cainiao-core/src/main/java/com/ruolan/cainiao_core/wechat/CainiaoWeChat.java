package com.ruolan.cainiao_core.wechat;

import android.app.Activity;

import com.ruolan.cainiao_core.app.Cainiao;
import com.ruolan.cainiao_core.app.ConfigType;
import com.ruolan.cainiao_core.wechat.callback.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by wuyinlei on 2017/10/18.
 */

public class CainiaoWeChat {

    static final String APP_ID = Cainiao.getConfiguration(ConfigType.WE_CHAT_APP_ID.name());
    static final String APP_SECRET = Cainiao.getConfiguration(ConfigType.WE_CHAT_APP_SECRET.name());

    private final IWXAPI mIWXAPI;
    private IWeChatSignInCallback mSignInCallback = null;

    private static final class Holder{
        private static final CainiaoWeChat INSTANCE = new CainiaoWeChat();
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public CainiaoWeChat onSignSuccess(IWeChatSignInCallback signInCallback) {
        mSignInCallback = signInCallback;
        return this;
    }

    public static CainiaoWeChat geiInstance(){
        return Holder.INSTANCE;
    }

    private CainiaoWeChat(){
        final Activity activity = Cainiao.getConfiguration(ConfigType.ACTIVITY.name());
        mIWXAPI = WXAPIFactory.createWXAPI(activity,APP_ID,true);
        mIWXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getIWXAPI(){
        return mIWXAPI;
    }

    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        mIWXAPI.sendReq(req);
    }


}
