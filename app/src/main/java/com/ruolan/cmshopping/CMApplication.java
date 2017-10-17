package com.ruolan.cmshopping;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.ruolan.cainiao_core.app.Cainiao;
import com.ruolan.cainiao_core.net.interceptor.DebugInterceptor;
import com.ruolan.cainiao_ec.database.DatabaseManager;
import com.ruolan.cainiao_ec.icon.FontEcModule;


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
                .withApiHost("http://127.0.0.1/")
                .withLoaderDelayed(1000)
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();

        DatabaseManager.getInstance().init(this);

        initStetho(this);

    }

    private void initStetho(Context context){
        Stetho.initialize(Stetho.newInitializerBuilder(context)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
        .build());
    }
}
