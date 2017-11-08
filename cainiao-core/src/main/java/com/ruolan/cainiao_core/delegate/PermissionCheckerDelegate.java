package com.ruolan.cainiao_core.delegate;

import android.Manifest;
import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.ruolan.cainiao_core.util.camera.CainiaoCamera;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by wuyinlei on 2017/10/12.
 *
 * @function
 */

@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {


    //不是直接调用方法
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera() {
        CainiaoCamera.start(this);
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
