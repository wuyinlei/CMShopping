package com.ruolan.cmshopping.generators;

import com.ruolan.annotations.PayEntryGenerator;
import com.ruolan.cainiao_core.wechat.templete.WXPayEntryTemplete;

/**
 * Created by wuyinlei on 2017/10/18.
 */

@PayEntryGenerator(
        packageName = "com.ruolan.cmshopping",
        payEntryTemplete = WXPayEntryTemplete.class
)
public interface WeChatPayEntry {
}
