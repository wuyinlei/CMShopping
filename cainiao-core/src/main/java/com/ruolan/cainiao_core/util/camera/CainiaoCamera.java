package com.ruolan.cainiao_core.util.camera;

import android.net.Uri;

import com.ruolan.cainiao_core.delegate.PermissionCheckerDelegate;
import com.ruolan.cainiao_core.util.file.FileUtil;

/**
 * Created by wuyinlei on 2017/11/8.
 *
 * @function 照相机调用类
 */

public class CainiaoCamera {

    public static Uri creareCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg"))
                        .getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }

}
