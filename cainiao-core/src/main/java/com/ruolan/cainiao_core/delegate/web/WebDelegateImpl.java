package com.ruolan.cainiao_core.delegate.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ruolan.cainiao_core.delegate.web.client.WebViewClientImpl;
import com.ruolan.cainiao_core.delegate.web.route.RouteKeys;
import com.ruolan.cainiao_core.delegate.web.route.Router;

/**
 * Created by wuyinlei on 2017/11/3.
 *
 * @function
 */

public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIPageLoadListener = null;


    public static WebDelegateImpl create(String url){
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    public void setIPageLoadListener(IPageLoadListener IPageLoadListener) {
        mIPageLoadListener = IPageLoadListener;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        super.onBindView(savedInstanceState, rootView);
        if (getUrl() != null){
            //用原生的方式模拟Web跳转  并进行页面加载

            Router.getInstance().loadPage(this,getUrl());

        }

    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return WebViewInitializer.createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        WebViewClientImpl viewClient = new WebViewClientImpl(this);
        viewClient.setIPageLoadListener(mIPageLoadListener);
        return viewClient;
    }

    @Override
    public WebChromeClient initWebChormeClient() {
        return new WebChromeClientImpl();
    }
}
