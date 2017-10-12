package com.ruolan.cmshopping;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.ruolan.cainiao_core.app.Cainiao;
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
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();
    }
}
