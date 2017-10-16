package com.ruolan.cainiao_core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.ruolan.cainiao_core.app.Cainiao;

/**
 * Created by wuyinlei on 2017/10/15.
 */

public class DimenUtils {

    public static int getScreenWidth() {
        final Resources resources = Cainiao.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Cainiao.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
