package com.ruolan.cainiao_core.app;


import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by wuyinlei on 2017/7/10.
 *
 * @function 全局配置项
 */

public class Configurator {

    private static final HashMap<String, Object> CAINIAO_CONFIGS = new HashMap<>();

    private static final ArrayList<IconFontDescriptor> ICON_FONT_DESCRIPTORS = new ArrayList<>();


    public Configurator() {
        CAINIAO_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
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

    public final Configurator withApiHost(String host) {
        CAINIAO_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICON_FONT_DESCRIPTORS.add(descriptor);
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

    private void checkConfigration() {
        final boolean isReady = (boolean) CAINIAO_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,cal configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfigration();
        return (T) CAINIAO_CONFIGS.get(key);
    }

}
