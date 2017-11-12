package com.ruolan.cmshopping;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.ruolan.cainiao_core.app.Cainiao;
import com.ruolan.cainiao_core.delegate.web.event.TestEvent;
import com.ruolan.cainiao_core.net.interceptor.AddCookieInterceptor;
import com.ruolan.cainiao_core.net.interceptor.DebugInterceptor;
import com.ruolan.cainiao_core.util.callback.CallbackManager;
import com.ruolan.cainiao_core.util.callback.CallbackType;
import com.ruolan.cainiao_core.util.callback.IGlobalCallback;
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

        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Cainiao.getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Cainiao.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Cainiao.getApplicationContext())) {
                            JPushInterface.stopPush(Cainiao.getApplicationContext());
                        }
                    }
                });

    }

    private void initStetho(Context context){
        Stetho.initialize(Stetho.newInitializerBuilder(context)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
        .build());
    }
}
