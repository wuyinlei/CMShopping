package com.ruolan.cainiao_core.app;


import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.ruolan.cainiao_core.delegate.web.event.Event;
import com.ruolan.cainiao_core.delegate.web.event.EventManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import butterknife.OnClick;
import okhttp3.Interceptor;

/**
 * Created by wuyinlei on 2017/7/10.
 *
 * @function 全局配置项
 */

public class Configurator {

    private static final HashMap<String, Object> CAINIAO_CONFIGS = new HashMap<>();

    private static final Handler HANDLER = new Handler();

    private static final ArrayList<Interceptor> CAINIAO_INTERCEPTORS = new ArrayList<>();


    private static final ArrayList<IconFontDescriptor> ICON_FONT_DESCRIPTORS = new ArrayList<>();


    public Configurator() {
        CAINIAO_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
        CAINIAO_CONFIGS.put(ConfigType.HANDLER.name(), HANDLER);
    }

    public final Configurator withLoaderDelayed(long delayed) {
        CAINIAO_CONFIGS.put(ConfigType.LOADER_DELAYED.name(), delayed);
        return this;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    final HashMap<String, Object> getCainiaoConfigs() {
        return CAINIAO_CONFIGS;
    }

    public final void configure() {
        initIcons();
        CAINIAO_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 添加域名
     *
     * @param host 域名访问地址
     * @return Configurator
     */
    public final Configurator withApiHost(String host) {
        CAINIAO_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    /**
     * 传入自定义的Icon
     *
     * @param descriptor IconFontDescriptor
     * @return Configurator
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICON_FONT_DESCRIPTORS.add(descriptor);
        return this;
    }

    /**
     * 添加自定义的拦截器
     *
     * @param interceptor 自定义的拦截器
     * @return Configurator
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        CAINIAO_INTERCEPTORS.add(interceptor);
        CAINIAO_CONFIGS.put(ConfigType.INTERCEPTOR.name(), CAINIAO_INTERCEPTORS);
        return this;
    }

    public final Configurator withWebHost(String webHost) {
        CAINIAO_CONFIGS.put(ConfigType.WEB_HOST.name(), webHost);
        return this;
    }

    /**
     * 添加和js交互定义好的名称
     *
     * @param name 名称
     * @return Configurator
     */
    public Configurator withJavaScriptInterface(String name) {
        CAINIAO_CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE.name(), name);
        return this;
    }

    public Configurator withWebEvent(@Nullable String name, @Nullable Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
//        CAINIAO_CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE.name(), name);
        return this;
    }


    /**
     * 添加拦截器
     *
     * @param interceptors 拦截器集合
     * @return Configurator
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        CAINIAO_INTERCEPTORS.addAll(interceptors);
        CAINIAO_CONFIGS.put(ConfigType.INTERCEPTOR.name(), CAINIAO_INTERCEPTORS);
        return this;
    }

    /**
     * 添加WeChat  AppId
     *
     * @param appId 微信AppId
     */
    public final Configurator withWeChatAppId(String appId) {
        CAINIAO_CONFIGS.put(ConfigType.WE_CHAT_APP_ID.name(), appId);
        return this;
    }

    /**
     * 添加WeChat  AppId
     *
     * @param appSceret 微信AppSceret
     */
    public final Configurator withWeChatAppSceret(String appSceret) {
        CAINIAO_CONFIGS.put(ConfigType.WE_CHAT_APP_SECRET.name(), appSceret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        CAINIAO_CONFIGS.put(ConfigType.ACTIVITY.name(), activity);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        CAINIAO_CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE.name(), name);
        return this;
    }


    private void initIcons() {
        if (ICON_FONT_DESCRIPTORS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICON_FONT_DESCRIPTORS.get(0));
            for (int i = 1; i < ICON_FONT_DESCRIPTORS.size(); i++) {
                initializer.with(ICON_FONT_DESCRIPTORS.get(i));
            }
        }
    }

    /**
     * 检查配置项是否正确
     */
    private void checkConfigration() {
        final boolean isReady = (boolean) CAINIAO_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,cal configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfigration();
        final Object value = CAINIAO_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) CAINIAO_CONFIGS.get(key);
    }

}
