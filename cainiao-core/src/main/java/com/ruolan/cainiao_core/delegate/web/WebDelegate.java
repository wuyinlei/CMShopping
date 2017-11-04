package com.ruolan.cainiao_core.delegate.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.ruolan.cainiao_core.app.Cainiao;
import com.ruolan.cainiao_core.app.ConfigType;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_core.delegate.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by wuyinlei on 2017/11/3.
 *
 * @function
 */

public abstract class WebDelegate extends CainiaoDelegate implements IWebViewInitializer{

    private WebView mWebView = null;

    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAbailable = false;
    private CainiaoDelegate mTopDelegate = null;


    public WebDelegate() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }

    public void setTopDelegate(CainiaoDelegate delegate){
        this.mTopDelegate = delegate;
    }

    public CainiaoDelegate getTopDelegate() {
        if (mTopDelegate == null)
            mTopDelegate = this;
        return mTopDelegate;
    }

    public abstract IWebViewInitializer setInitializer();

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);

                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChormeClient());
                final String name = Cainiao.getConfiguration(ConfigType.JAVASCRIPT_INTERFACE.name());
                mWebView.addJavascriptInterface(CainiaoWebInterface.create(this), name);
                mIsWebViewAbailable = true;
            } else {
                throw new NullPointerException("Initializer is NULL!");
            }
        }
    }

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("url is null");
        }
        return mUrl;
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WEBVIEW IS NULL!!");
        }
        return mIsWebViewAbailable ? mWebView : null;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
