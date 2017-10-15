package com.ruolan.cainiao_core.app;

import android.content.Context;
import android.content.res.Resources;

import java.util.HashMap;

/**
 * Created by wuyinlei on 2017/7/10.
 *
 * @function
 */

public final class Cainiao {


    public static Configurator init(Context context){
        getConfitgurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());

        return Configurator.getInstance();
    }

    public static HashMap<String,Object> getConfitgurations(){
        return Configurator.getInstance().getCainiaoConfigs();
    }



    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    private static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static Context getApplication(){
        return (Context) getConfitgurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

}
