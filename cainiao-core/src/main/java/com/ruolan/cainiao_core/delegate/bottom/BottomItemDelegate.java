package com.ruolan.cainiao_core.delegate.bottom;

import android.widget.Toast;

import com.ruolan.cainiao_core.R;
import com.ruolan.cainiao_core.app.Cainiao;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;

/**
 * Created by wuyinlei on 2017/10/16.
 */

public abstract class BottomItemDelegate extends CainiaoDelegate {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Cainiao.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
