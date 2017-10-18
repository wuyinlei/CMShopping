package com.ruolan.cainiao_core.app;

/**
 * Created by wuyinlei on 2017/10/16.
 */

public interface IUserChecker {

    /**
     * 已经登录
     */
    void onSignIn();

    /**
     * 用户退出  未登录
     */
    void onNotSignIn();

}
