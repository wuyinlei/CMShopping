package com.ruolan.cainiao_core.delegate.web.event;

import android.widget.Toast;

/**
 * Created by wuyinlei on 2017/11/4.
 *
 * @function 测试event
 */

public class TestEvent extends Event {

    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), params, Toast.LENGTH_SHORT).show();
        return null;
    }
}
