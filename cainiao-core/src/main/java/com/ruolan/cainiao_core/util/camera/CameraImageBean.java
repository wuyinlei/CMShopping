package com.ruolan.cainiao_core.util.camera;

import android.net.Uri;

/**
 * Created by wuyinlei on 2017/11/8.
 *
 * @function 储存中间值
 */

public class CameraImageBean {

    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }

}
