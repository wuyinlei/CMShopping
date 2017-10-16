package com.ruolan.cainiao_core.util.timer;

import java.util.TimerTask;

/**
 * Created by wuyinlei on 2017/10/16.
 */

public class BaseTimerTask extends TimerTask{

    private ITimerListener mITimerListener = null;

    public void setITimerListener(ITimerListener ITimerListener) {
        mITimerListener = ITimerListener;
    }

    public BaseTimerTask(ITimerListener ITimerListener) {
        mITimerListener = ITimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null){
            mITimerListener.onTimer();
        }
    }
}
