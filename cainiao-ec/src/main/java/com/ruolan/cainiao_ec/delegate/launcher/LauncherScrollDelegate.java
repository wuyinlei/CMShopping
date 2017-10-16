package com.ruolan.cainiao_ec.delegate.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TableRow;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.cainiao.cainiao_uiu.ui.launcher.LauncherHolderCreator;
import com.cainiao.cainiao_uiu.ui.launcher.ScrollIlauncherTag;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
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

    private void initBanner() {
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



        }
    }
}
