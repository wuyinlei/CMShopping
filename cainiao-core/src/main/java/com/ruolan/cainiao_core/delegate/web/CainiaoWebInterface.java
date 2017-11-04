package com.ruolan.cainiao_core.delegate.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSONObject;
import com.ruolan.cainiao_core.delegate.web.event.Event;
import com.ruolan.cainiao_core.delegate.web.event.EventManager;

/**
 * Created by wuyinlei on 2017/11/3.
 *
 * @function
 */

public class CainiaoWebInterface {

    private final WebDelegate DELEGATE;

    public CainiaoWebInterface(WebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    static CainiaoWebInterface create(WebDelegate delegate) {
        return new CainiaoWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params) {
        final String action = JSONObject.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }

}
