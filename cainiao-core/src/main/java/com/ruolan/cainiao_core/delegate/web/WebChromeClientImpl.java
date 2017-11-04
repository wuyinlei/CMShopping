package com.ruolan.cainiao_core.delegate.web;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by wuyinlei on 2017/11/4.
 *
 * @function
 */

public class WebChromeClientImpl extends WebChromeClient{


    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }


}
