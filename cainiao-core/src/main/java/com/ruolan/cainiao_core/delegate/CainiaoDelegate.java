package com.ruolan.cainiao_core.delegate;

/**
 * Created by wuyinlei on 2017/10/12.
 */

public abstract class CainiaoDelegate extends PermissionCheckerDelegate {

    public <T extends CainiaoDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }

}
