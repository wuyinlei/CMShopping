package com.ruolan.cainiao_core.delegate.web.event;

import com.ruolan.cainiao_core.util.log.CainiaoLogger;

/**
 * Created by wuyinlei on 2017/11/
 *
 * @function
 */

public class UndefineEvent extends Event {


    @Override
    public String execute(String params) {
        CainiaoLogger.d("UndefineEvent", params);
        return null;
    }
}
