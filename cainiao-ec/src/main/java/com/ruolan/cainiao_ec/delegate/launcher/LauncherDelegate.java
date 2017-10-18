package com.ruolan.cainiao_ec.delegate.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.cainiao.cainiao_uiu.ui.launcher.ScrollIlauncherTag;
import com.ruolan.cainiao_core.app.AccountManager;
import com.ruolan.cainiao_core.app.IUserChecker;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_core.ui.ILauncherListener;
import com.ruolan.cainiao_core.ui.OnLauncherFinishTag;
import com.ruolan.cainiao_core.util.storage.CainiaoPreference;
import com.ruolan.cainiao_core.util.timer.BaseTimerTask;
import com.ruolan.cainiao_core.util.timer.ITimerListener;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;
import com.ruolan.cainiao_ec.delegate.main.EcBottomDelegate;
import com.ruolan.cainiao_ec.delegate.sign.SignInDelegate;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wuyinlei on 2017/10/16.
 *
 * @function 启动界面
 */

public class LauncherDelegate extends CainiaoDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;

    private ILauncherListener mILauncherListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    /**
     * 检查是否展示启动界面轮播图
     */
    private void checkIsShowScroll() {
        if (!CainiaoPreference.getAppFlag(ScrollIlauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //检查用户是否登录  跳转逻辑
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGINED);
                    }
                    //已经登录了 这个时候处理登录的逻辑
//                    getSupportDelegate().startWithPop(new EcBottomDelegate());
                }

                @Override
                public void onNotSignIn() {
                    //用户还没有登录  这个时候处理未登录的逻辑
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
//                    getSupportDelegate().startWithPop(new SignInDelegate());
                }
            });

        }

    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount <= 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
