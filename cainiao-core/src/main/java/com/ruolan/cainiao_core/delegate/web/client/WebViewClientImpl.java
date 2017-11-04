package com.ruolan.cainiao_core.delegate.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ruolan.cainiao_core.app.Cainiao;
import com.ruolan.cainiao_core.app.ConfigType;
import com.ruolan.cainiao_core.delegate.web.IPageLoadListener;
import com.ruolan.cainiao_core.delegate.web.WebDelegate;
import com.ruolan.cainiao_core.delegate.web.route.Router;
import com.ruolan.cainiao_core.ui.CainiaoLoader;
import com.ruolan.cainiao_core.util.log.CainiaoLogger;
import com.ruolan.cainiao_core.util.storage.CainiaoPreference;


/**
 * Created by wuyinlei on 2017/11/3.
 *
 * @function
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Cainiao.getHandler();

    public void setIPageLoadListener(IPageLoadListener IPageLoadListener) {
        this.mIPageLoadListener = IPageLoadListener;
    }

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
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null)
            mIPageLoadListener.onLoadStart();

        CainiaoLoader.showLoading(view.getContext());
    }

    //获取游览器的cookie
    private void syncCookie(){
        final CookieManager cookieManager = CookieManager.getInstance();
        /**
         * 注意这里的cookie和api请求的cookie是不一样的   这个在网页中是不可见的
         */
        final String webHost = Cainiao.getConfiguration(ConfigType.WEB_HOST.name());
        if (webHost != null){
            if (cookieManager.hasCookies()) {
                final String cookieStr = cookieManager.getCookie(webHost);
                if (cookieStr != null && !cookieStr.equals("")) {
                    CainiaoPreference.addCustomAppProfile("cookie", cookieStr);
                }
            }
        }


    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        syncCookie();

        if (mIPageLoadListener != null)
            mIPageLoadListener.onloadFinish();

        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                CainiaoLoader.stopLoading();
            }
        }, 1000);


    }

}
