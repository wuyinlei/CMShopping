package com.cainiao.cainiao_ui.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by wuyinlei on 2017/10/25.
 */

public class HolderCreator implements CBViewHolderCreator<ImageHolder>{


    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
