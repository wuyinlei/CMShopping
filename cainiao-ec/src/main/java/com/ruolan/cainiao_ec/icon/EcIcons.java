package com.ruolan.cainiao_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by wuyinlei on 2017/10/12.
 */

public enum  EcIcons implements Icon{
    icon_scan('\ue606'),
    icon_all_pay('\ue606');


    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_','-');
    }

    @Override
    public char character() {
        return character;
    }
}
