package com.ruolan.cainiao_core.delegate.web.event;

import android.content.Context;

import com.ruolan.cainiao_core.delegate.CainiaoDelegate;

/**
 * Created by wuyinlei on 2017/11/4.
 */

public abstract class Event implements IEvent {

    private Context mContext = null;
    private String mAction = null;
    private CainiaoDelegate mDelegate = null;
    private String mUrl = null;


    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        mAction = action;
    }

    public CainiaoDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(CainiaoDelegate delegate) {
        mDelegate = delegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
