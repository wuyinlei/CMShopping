package com.ruolan.cmshopping;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.ruolan.cainiao_core.app.Cainiao;
import com.ruolan.cainiao_core.delegate.web.event.TestEvent;
import com.ruolan.cainiao_core.net.interceptor.AddCookieInterceptor;
import com.ruolan.cainiao_core.net.interceptor.DebugInterceptor;
import com.ruolan.cainiao_ec.database.DatabaseManager;
import com.ruolan.cainiao_ec.icon.FontEcModule;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by wuyinlei on 2017/7/10.
 *
 * @function 全局的Application
 */

public class CMApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Cainiao.init(this)
                .withApiHost("http://easy-mock.com/mock/")
                .withLoaderDelayed(1000)
                .withInterceptor(new DebugInterceptor("dddddd",R.raw.test))
                //添加Cookie的拦截器
                .withInterceptor(new AddCookieInterceptor())
                //添加Cookie同步拦截器
                .withWebHost("http://github.com")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withWeChatAppId("wxfcdcecd9df8e0faa")
                .withWeChatAppSceret("a0560f75335b06e3ebea70f29ff219bf")
                .withJavaScriptInterface("cainiao")
                .withWebEvent("test", new TestEvent())
                .configure();

        DatabaseManager.getInstance().init(this);

        initStetho(this);

        JPushInterface.setDebugMode(true);

        JPushInterface.init(this);

    }

    private void initStetho(Context context){
        Stetho.initialize(Stetho.newInitializerBuilder(context)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
        .build());
    }
}
