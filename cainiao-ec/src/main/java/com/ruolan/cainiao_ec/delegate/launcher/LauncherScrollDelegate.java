package com.ruolan.cainiao_ec.delegate.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TableRow;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.cainiao.cainiao_uiu.ui.launcher.LauncherHolderCreator;
import com.cainiao.cainiao_uiu.ui.launcher.ScrollIlauncherTag;
import com.ruolan.cainiao_core.app.AccountManager;
import com.ruolan.cainiao_core.app.IUserChecker;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_core.ui.ILauncherListener;
import com.ruolan.cainiao_core.ui.OnLauncherFinishTag;
import com.ruolan.cainiao_core.util.storage.CainiaoPreference;
import com.ruolan.cainiao_ec.R;

import java.util.ArrayList;

/**
 * Created by wuyinlei on 2017/10/16.
 *
 * @function 首次轮播图
 */

public class LauncherScrollDelegate extends CainiaoDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private ILauncherListener mILauncherListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    private void initBanner() {
        if (INTEGERS.size() == 0) {
            INTEGERS.add(R.mipmap.launcher_01);
            INTEGERS.add(R.mipmap.launcher_02);
            INTEGERS.add(R.mipmap.launcher_03);
            INTEGERS.add(R.mipmap.launcher_04);
            INTEGERS.add(R.mipmap.launcher_05);

            mConvenientBanner.setPages(new LauncherHolderCreator(), INTEGERS)
                    .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                    .setOnItemClickListener(this)
                    .setCanLoop(true);
        }
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(_mActivity);
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        if (position == INTEGERS.size() - 1) {
            //如果点击的是最后一个图片
            CainiaoPreference.setAppFlag(ScrollIlauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登录
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
}
