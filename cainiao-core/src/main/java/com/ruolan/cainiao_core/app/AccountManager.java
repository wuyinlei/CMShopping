package com.ruolan.cainiao_core.app;

import com.ruolan.cainiao_core.util.storage.CainiaoPreference;

/**
 * Created by wuyinlei on 2017/10/16.
 *
 * @function 账户登录管理类
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    public static void saveSignState(boolean state) {
        CainiaoPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return CainiaoPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker userChecker) {
        if (isSignIn()) {
            userChecker.onSignIn();
        } else {
            userChecker.onNotSignIn();
        }
    }

}
