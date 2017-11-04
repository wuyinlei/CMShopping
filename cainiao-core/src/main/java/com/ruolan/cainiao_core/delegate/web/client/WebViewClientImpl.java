package com.ruolan.cainiao_core.delegate.web.client;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ruolan.cainiao_core.delegate.web.WebDelegate;
import com.ruolan.cainiao_core.delegate.web.route.Router;
import com.ruolan.cainiao_core.ui.CainiaoLoader;
import com.ruolan.cainiao_core.util.log.CainiaoLogger;

/**
 * Created by wuyinlei on 2017/11/3.
 *
 * @function
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        CainiaoLogger.d("shouldOverrideUrlLoading", "url" + url);
        return Router.getInstance().handleWebUrl(DELEGATE,url);
    }
}
